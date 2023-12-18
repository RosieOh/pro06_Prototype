package com.springbootstart.controller;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.service.BoardService;
import com.springbootstart.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Log4j2
@Controller
public class BoardController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/api/list")
    @ResponseBody
    public List<BoardDTO> listAll() {
        List<BoardDTO> boardList = boardService.findAll();
        return boardList;
    }

    @GetMapping("/api/read")
    @ResponseBody
    public BoardDTO findByBno(Long bno, String boardType) {
        BoardDTO board = boardService.findByBno(bno, boardType) ;
        return board;
    }

    @PostMapping("/api/register")
    @ResponseBody
    public Long apiboardWrite(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("board POST register...");
        if(bindingResult.hasErrors()) {
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        Long bno = boardService.register(boardDTO);
        return bno;
    }

    @PostMapping("/api/modify")
    @ResponseBody
    public BoardDTO modifyPro(@Valid BoardDTO boardDTO,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        log.info("board modify post........" + boardDTO);
        if(bindingResult.hasErrors()) {
            log.info("has errors.........");
            String link = "bno="+boardDTO.getBno();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return boardDTO;
        }
        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addFlashAttribute("bno", boardDTO.getBno());
        return boardDTO;
    }

    @PostMapping("/api/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes) {
        log.info("remove post....." + bno);
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/apiboard/list";
    }

    @GetMapping({"/board", "/board/list"})
    public String boardListAll(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.listNotice(pageRequestDTO);
        List<BoardDTO> boardList = boardService.findAll();

        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'TEACHER')")
    @GetMapping("/board/read")
    public String read(Long bno, String boardType, PageRequestDTO pageRequestDTO, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno, boardType);
        log.info(boardDTO);
        model.addAttribute("dto", boardDTO);
        return "board/read";
    }

    @GetMapping("/board/register")
    public String registerForm(){
        return "board/register";
    }

    @PostMapping("/board/register")
    public String boardRegister(@Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("board POST register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors..........");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }
        log.info(boardDTO);
        Long bno = boardService.register(boardDTO);
        return "redirect:/board/list";
    }

    @GetMapping("/modify")
    public String modifyForm(Long bno, String boardType, Model model) {
        BoardDTO boardDTO = boardService.findByBno(bno, boardType);
        model.addAttribute("dto", boardDTO);
        return "board/modify";
    }

    @PostMapping("/board/modify")
    public String modify( @Valid BoardDTO boardDTO,
                          BindingResult bindingResult,
                          PageRequestDTO pageRequestDTO,
                          RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("bno", boardDTO.getBno());
            return "redirect:/board/modify?"+link;
        }
        boardService.modify(boardDTO);
        redirectAttributes.addFlashAttribute("result", "modified");
        redirectAttributes.addAttribute("bno", boardDTO.getBno());
        return "redirect:/board/read";
    }

    @PostMapping("/remove")
    public String remove(BoardDTO boardDTO, RedirectAttributes redirectAttributes) {
        Long bno = boardDTO.getBno();
        boardService.remove(bno);
        List<String> fileNames = boardDTO.getFileNames();
        if(fileNames != null && fileNames.size() > 0) {
            removeFiles(fileNames);
        }
        redirectAttributes.addFlashAttribute("result", "removed");
        return "redirect:/board/list";
    }

    public void removeFiles(List<String> files){
        for (String fileName:files) {
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();
            try {
                String contentType = Files.probeContentType(resource.getFile().toPath());
                resource.getFile().delete();
                if (contentType.startsWith("image")) {                  //섬네일이 존재한다면
                    File thumbnailFile = new File(uploadPath + File.separator + "s_" + fileName);
                    thumbnailFile.delete();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
