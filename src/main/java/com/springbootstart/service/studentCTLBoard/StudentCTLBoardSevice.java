package com.springbootstart.service.studentCTLBoard;

import com.springbootstart.dto.StudentCTLBoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;

public interface StudentCTLBoardSevice {

    Long register(StudentCTLBoardDTO studentCTLBoardDTO);

    StudentCTLBoardDTO readOne(Long sbno);

    void modify(StudentCTLBoardDTO studentCTLBoardDTO);

    void remove(Long sbno);

    PageResponseDTO<StudentCTLBoardDTO> list(PageRequestDTO pageRequestDTO);

}
