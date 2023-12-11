package com.springbootstart.controller;

import com.springbootstart.dto.StudentCTLBoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.service.studentCTLBoard.StudentCTLBoardSevice;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/studentCTLBoard/*")
public class studentCTLBoardController {

    private final StudentCTLBoardSevice studentCTLBoardSevice;

    @GetMapping("list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<StudentCTLBoardDTO> responseDTO = studentCTLBoardSevice.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        log.info(responseDTO);
        return "studentCTLBoard/list";
    }

    @GetMapping("register")
    public String registerGET() {
        return "studentCTLBoard/register";
    }

    @PostMapping("register")
    public String registerPost(@Valid StudentCTLBoardDTO studentCTLBoardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("studentCTLBoard Post Register.....");

        if(bindingResult.hasErrors()) {
            log.info("has errors............");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/studentCTLBoard/register";
        }

        log.info(studentCTLBoardDTO);
        Long sbno = studentCTLBoardSevice.register(studentCTLBoardDTO);
        redirectAttributes.addFlashAttribute("result", sbno);

        return "redirect:/studentCTLBoard/list";
    }

    @GetMapping("read")
    public String read(Long sbno, PageRequestDTO pageRequestDTO, Model model) {
        StudentCTLBoardDTO studentCTLBoardDTO = studentCTLBoardSevice.readOne(sbno);
        log.info(studentCTLBoardDTO);
        model.addAttribute("dto", studentCTLBoardDTO);
        return "studentCTLBoard/read";
    }

    @GetMapping("modify")
    public String modifyForm(Long sbno, PageRequestDTO pageRequestDTO, Model model) {
        StudentCTLBoardDTO studentCTLBoardDTO = studentCTLBoardSevice.readOne(sbno);
        model.addAttribute("dto", studentCTLBoardDTO);
        return "studentCTLBoard/modify";
    }


}
