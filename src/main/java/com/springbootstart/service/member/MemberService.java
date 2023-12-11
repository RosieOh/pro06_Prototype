package com.springbootstart.service.member;

import com.springbootstart.dto.MemberJoinDTO;
import com.springbootstart.entity.Member;;

public interface MemberService {
    static class MidExistException extends Exception {}
    Member existByEmail(String email);
    void join(MemberJoinDTO memberJoinDTO) ;

    void changePw(MemberJoinDTO memberJoinDTO);


}