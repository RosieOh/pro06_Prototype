package com.springbootstart.controller;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.entity.Member;
import com.springbootstart.repository.MemberRepository;
import com.springbootstart.service.BoardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
public class studentCTLBoardController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping({"/studentctl", "/studentctl/list"})
    public String boardListAll(PageRequestDTO pageRequestDTO, Model model, Principal principal) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.listStudentCTL(pageRequestDTO);
        List<BoardDTO> boardList = boardService.findAll();
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("boardList", boardList);
        String mid = principal.getName();
        Member member = memberRepository.findByMid(mid);
        model.addAttribute("member", member);
        model.addAttribute("principal", principal);
        return "studentctl/list";
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/studentctl/read")
    public String readStudentctl(Long bno, String boardType, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno, boardType);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
        return "studentctl/read";
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/studentctl/register")
    public String registerForm(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        return "studentctl/register";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/studentctl/register")
    public String studentctlRegister(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal, Model model) {
        log.info("board POST register.......");

        if (bindingResult.hasErrors()) {
            log.info("has errors..........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }

        // 현재 로그인한 사용자를 작성자로 설정
        boardDTO.setWriter(principal.getName());

        log.info(boardDTO);
        log.info("Writer from principal: " + principal.getName());
        log.info("Writer in boardDTO: " + boardDTO.getWriter());

        // Null 체크 추가
        if (boardDTO.getWriter() != null) {
            log.info("Writer in boardDTO is not null");
        } else {
            log.info("Writer in boardDTO is null");
        }
        Long bno = boardService.register(boardDTO);
        model.addAttribute("dto", bno);
        return "redirect:/studentctl/list";
    }

    @GetMapping("/studentctl/modify")
    public String modifyForm(Long bno, String boardType, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno, boardType);
        model.addAttribute("dto", boardDTO);
        return "studentctl/modify";
    }

    @PostMapping("/studentctl/modify")
    public String modify(@Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("bno", boardDTO.getBno());
        }

        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/studentctl/read";
    }

    @PostMapping("/studentctl/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
        Long bno = boardDTO.getBno();
        boardService.remove(bno);
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0) {
            removeFiles(fileNames);
        }
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/studentctl/list";
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
