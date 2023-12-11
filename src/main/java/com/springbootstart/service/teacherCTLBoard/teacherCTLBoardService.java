package com.springbootstart.service.teacherCTLBoard;

import com.springbootstart.dto.TeacherCTLBoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;

public interface teacherCTLBoardService {

    Long register(TeacherCTLBoardDTO teacherCTLBoardDTO);

    TeacherCTLBoardDTO readOne(Long tbno);

    void modify(TeacherCTLBoardDTO teacherCTLBoardDTO);

    void remove(Long tbno);

    PageResponseDTO<TeacherCTLBoardDTO> list(PageRequestDTO pageRequestDTO);
}
