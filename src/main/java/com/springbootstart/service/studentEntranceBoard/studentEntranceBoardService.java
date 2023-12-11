package com.springbootstart.service.studentEntranceBoard;

import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;

public interface studentEntranceBoardService {

    Long register(StudentEntranceBoardDTO studentEntranceBoardDTO);

    StudentEntranceBoardDTO readOne(Long sebno);

    void modify(StudentEntranceBoardDTO studentEntranceBoardDTO);

    void remove(Long sebno);

    PageResponseDTO<StudentEntranceBoardDTO> list(PageRequestDTO pageRequestDTO);
}
