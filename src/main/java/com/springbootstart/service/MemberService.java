package com.springbootstart.service;

import com.springbootstart.dto.MemberJoinDTO;
import com.springbootstart.dto.MemberSecurityDTO;
import com.springbootstart.entity.Member;
import org.springframework.security.crypto.password.PasswordEncoder;;import java.util.Optional;

public interface MemberService {
    void createAdminMember();

    static class MidExistException extends Exception {}
    Member existByEmail(String email);
    void join(MemberJoinDTO memberJoinDTO) ;

    void changePw(MemberJoinDTO memberJoinDTO);
    public PasswordEncoder passwordEncoder();



}