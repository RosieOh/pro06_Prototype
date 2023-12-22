package com.springbootstart.controller;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.FileDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.entity.Member;
import com.springbootstart.repository.MemberRepository;
import com.springbootstart.service.BoardService;
import com.springbootstart.service.FileService;
import com.springbootstart.service.MemberService;
import com.springbootstart.util.MD5Generator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class studentEntranceController {

    @Value("${upload.path}")
    private String uploadPath;

    private final BoardService boardService;

    private final MemberRepository memberRepository;

    private final FileService fileService;

    @GetMapping({"/studententrance", "/studententrance/list"})
    public String boardListAll(Model model, Principal principal) {
        String boardType = "StudentEntrace";
        List<BoardDTO> boardList = boardService.findByBoardType(boardType);
        if(principal != null) {
            model.addAttribute("username", principal.getName());
        }
        model.addAttribute("boardList", boardList);
        String mid = principal.getName();
        Member member = memberRepository.findByMid(mid);
        model.addAttribute("member", member);
        model.addAttribute("principal", principal);
        return "studententrance/list";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/studententrance/read")
    public String readStudententrance(Long bno, Long id, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno);
        FileDTO fileDTO = fileService.getFile(id);
        log.info(fileDTO);
        log.info(boardDTO);
        model.addAttribute("boardList", boardDTO);
        model.addAttribute("fileList", fileDTO);
        return "studententrance/read";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/studententrance/register")
    public String registerForm(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        String mid = principal.getName();
        Member member = memberRepository.findByMid(mid);
        model.addAttribute("writer", member.getMname());
        return "studententrance/register";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/studententrance/register")
    public String studententranceRegister(@Valid BoardDTO boardDTO,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes,
                                 Principal principal,
                                 Model model,
                                 @RequestParam("file") MultipartFile files) {
        log.info("board POST register.......");
        log.info("이름 어디 갔노" + boardDTO.getWriter());
        if (bindingResult.hasErrors()) {
            log.info("has errors..........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        try {
            String originFilename = files.getOriginalFilename();
            String filename = new MD5Generator(originFilename).toString();
            String savePath = System.getProperty("user.dir") + "\\files";
            if(!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdirs();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String filePath = savePath + "\\" + filename;
            files.transferTo(new File(filePath));

            FileDTO fileDTO = new FileDTO();
            fileDTO.setOriginFilename(originFilename);
            fileDTO.setFilename(filename);
            fileDTO.setFilePath(filePath);

            Long fileId = fileService.saveFile(fileDTO);
            boardDTO.setFileId(fileId);
            boardService.register(boardDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/studententrance/list";
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/studententrance/modify")
    private String modifyForm(Long bno,Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno);
        model.addAttribute("dto", boardDTO);
        return "studententrace/modify";
    }

    @PostMapping("/studententrance/modify")
    private String modifyStudententrance(@Valid BoardDTO boardDTO,
                                         BindingResult bindingResult,
                                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("bno", boardDTO.getBno());
        }

        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/studententrance/read";
    }

    @PostMapping("/studententrace/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
        Long bno = boardDTO.getBno();
        boardService.remove(bno);
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0) {
            removeFiles(fileNames);
        }
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/studententrance/list";
    }

    private void removeFiles(List<String> files) {
        for (String fileName:files) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();
            try {
                String contentType = Files.probeContentType(resource.getFile().toPath());
                resource.getFile().delete();
                if (contentType.startsWith("image")) {
                    File thumbnamilFile = new File(uploadPath + File.separator+"s_"+ fileName);
                    thumbnamilFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
