package com.springbootstart.service.member;

import com.springbootstart.constant.MemberRole;
import com.springbootstart.dto.MemberJoinDTO;
import com.springbootstart.entity.Member;
import com.springbootstart.repository.MemberRepository;
import com.springbootstart.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final ModelMapper modelMapper;

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final ProfileRepository profileRepository;

    @Override
    public void join(MemberJoinDTO memberJoinDTO) {

        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);
        log.info("=======================");
        log.info(member);
        log.info(member.getRoleSet());
        memberRepository.save(member);


    }


    @Override
    public Member existByEmail(String email) {
        return memberRepository.existsMemberByEmail(email);
    }

    @Override
    public void changePw(MemberJoinDTO memberJoinDTO) {

        Optional<Member> result = memberRepository.findById(String.valueOf(memberJoinDTO.getMid()));
        Member member = result.orElseThrow();
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        memberRepository.save(member);
    }
}
