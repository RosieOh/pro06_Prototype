package com.springbootstart.service.teacherEntranceBoard;

import com.springbootstart.dto.entranceBoard.TeacherEntranceBoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;

public interface teacherEntranceBoardService {

    Long register(TeacherEntranceBoardDTO teacherEntranceBoardDTO);

    TeacherEntranceBoardDTO readOne(Long tebno);

    void modify(TeacherEntranceBoardDTO teacherEntranceBoardDTO);

    void remove(Long tebno);

    PageResponseDTO<TeacherEntranceBoardDTO> list(PageRequestDTO pageRequestDTO);
}
