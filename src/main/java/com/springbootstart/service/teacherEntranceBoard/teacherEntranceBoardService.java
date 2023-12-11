package com.springbootstart.service.teacherEntranceBoard;

import com.springbootstart.dto.TeacherEntranceBoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;

public interface teacherEntranceBoardService {

    Long register(TeacherEntranceBoardDTO teacherEntranceBoardDTO);

    TeacherEntranceBoardDTO readOne(Long tebno);

    void modify(TeacherEntranceBoardDTO teacherEntranceBoardDTO);

    void remove(Long tebno);

    PageResponseDTO<TeacherEntranceBoardDTO> list(PageRequestDTO pageRequestDTO);
}
