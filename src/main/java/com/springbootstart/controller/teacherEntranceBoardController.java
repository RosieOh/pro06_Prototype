package com.springbootstart.controller;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.service.BoardService;
import com.springbootstart.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Log4j2
@Controller
public class teacherEntranceBoardController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @GetMapping({"/teacherent", "/teacherent/list"})
    public String boardListAll(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.listTeacherEntrance(pageRequestDTO);
        List<BoardDTO> boardList = boardService.findAll();

        log.info(responseDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("boardList", boardList);
        return "teacherentrance/list";
    }
}
