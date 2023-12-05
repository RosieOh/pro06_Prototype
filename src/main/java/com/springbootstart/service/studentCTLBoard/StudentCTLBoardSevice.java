package com.springbootstart.service.studentCTLBoard;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.ctlBoard.StudentCTLBoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;

public interface StudentCTLBoardSevice {

    Long register(StudentCTLBoardDTO studentCTLBoardDTO);

    StudentCTLBoardDTO readOne(Long sbno);

    void modify(StudentCTLBoardDTO studentCTLBoardDTO);

    void remove(Long sbno);

    PageResponseDTO<StudentCTLBoardDTO> list(PageRequestDTO pageRequestDTO);

}
