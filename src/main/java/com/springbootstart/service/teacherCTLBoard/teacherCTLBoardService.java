package com.springbootstart.service.teacherCTLBoard;

import com.springbootstart.dto.ctlBoard.TeacherCTLBoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;

public interface teacherCTLBoardService {

    Long register(TeacherCTLBoardDTO teacherCTLBoardDTO);

    TeacherCTLBoardDTO readOne(Long tbno);

    void modify(TeacherCTLBoardDTO teacherCTLBoardDTO);

    void remove(Long tbno);

    PageResponseDTO<TeacherCTLBoardDTO> list(PageRequestDTO pageRequestDTO);
}
