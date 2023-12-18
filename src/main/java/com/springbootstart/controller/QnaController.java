package com.springbootstart.controller;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.entity.Member;
import com.springbootstart.repository.MemberRepository;
import com.springbootstart.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
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
@RequiredArgsConstructor
public class QnaController {

    @Value("${upload.path}")
    private String uploadPath;

    private final BoardService boardService;
    private final MemberRepository memberRepository;

    @GetMapping({"/qna", "/qna/list"})
    public String qnaListAll(PageRequestDTO pageRequestDTO, Model model, Principal principal) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.listQna(pageRequestDTO);
        List<BoardDTO> boardList = boardService.findAll();
        if(principal != null) {
            model.addAttribute("username", principal.getName());
        }
        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("boardList", boardList);
        String mid = principal.getName();
        Member member = memberRepository.findByMid(mid);
        model.addAttribute("member", member);
        return "qna/list";
    }

    @GetMapping("/qna/read")
    public String readQna(Long bno, String boardType, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno, boardType);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
        return "qna/read";
     }

    @GetMapping("/qna/register")
    public String registerForm(Model model, Principal principal) {
        model.addAttribute("principal", principal);
        return "qna/register";
    }

    @PostMapping("/qna/register")
    public String qnaRegister(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Principal principal, Model model) {
        log.info("Qna POST register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors..........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        log.info(boardDTO);
        return "redirect:/qna/list";
    }

    @GetMapping("/qna/modify")
    public String modifyForm(Long bno, String boardType, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno, boardType);
        model.addAttribute("dto", boardDTO);
        return "qna/modify";
    }

    @PostMapping("/qna/modify")
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
        return "redirect:/qna/read";
    }

    @PostMapping("/qna/remove")
    public String removeQna(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
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
