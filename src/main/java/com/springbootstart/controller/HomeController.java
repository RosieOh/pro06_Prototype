package com.springbootstart.controller;

import com.springbootstart.entity.Member;
import com.springbootstart.entity.Profile;
import com.springbootstart.repository.MemberRepository;
import com.springbootstart.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/")
    public String home(Principal principal,Model model) {
        Member member = new Member();
        if (principal != null) {
            String mid = principal.getName();
            member = memberRepository.findByMid(mid);
        }
        model.addAttribute("member", member);
        memberService.createAdminMember(); // 관리자 회원 생성 메서드 호출
        return "index";
    }
}
