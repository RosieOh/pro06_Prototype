package com.springbootstart.controller;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.service.board.BoardService;
import com.springbootstart.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Log4j2
@Controller
public class NoticeController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @GetMapping({"/notice", "/notice/list"})
    public String NoticeListAll(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.listNotice(pageRequestDTO);
        List<BoardDTO> boardList = boardService.findAll();

        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("boardList", boardList);
        return "notice/list";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'TEACHER')")
    @GetMapping("/notice/read")
    public String readNotice(Long bno, String boardType, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno, boardType);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
        return "notice/read";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/notice/register")
    public String registerForm() {
        return "notice/register";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/notice/register")
    public String noticeRegister(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("board POST register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors..........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);
        return "redirect:/notice/list";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/notice/modify")
    public String modifyForm(Long bno, String boardType, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno, boardType);
        model.addAttribute("dto", boardDTO);
        return "notice/modify";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/notice/modify")
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
        return "redirect:/notice/read";
    }

    @PostMapping("/notice/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
        Long bno = boardDTO.getBno();
        boardService.remove(bno);
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0) {
            removeFiles(fileNames);
        }
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/notice/list";
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
