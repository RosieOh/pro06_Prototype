package com.springbootstart.controller;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.entity.Member;
import com.springbootstart.repository.MemberRepository;
import com.springbootstart.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
public class FaqController {

    private final BoardService boardService;

    private final MemberRepository memberRepository;

    // 리스트만 불러오고 테스트 DB로 해결하는 걸로
    @GetMapping({"/faq", "/faq/list"})
    public String FaqListAll(PageRequestDTO pageRequestDTO, Model model, Principal principal) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.listNotice(pageRequestDTO);
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
        return "faq/list";
    }
}