package com.springbootstart.service.studentEntranceBoard;

import com.springbootstart.domain.StudentEntranceBoard;
import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.entranceBoard.StudentEntranceBoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;

public interface studentEntranceBoardService {

    Long register(StudentEntranceBoardDTO studentEntranceBoardDTO);
    StudentEntranceBoardDTO readOne(Long sebno);

    void modify(StudentEntranceBoardDTO studentEntranceBoardDTO);

    void remove(Long sebno);

    PageResponseDTO<StudentEntranceBoardDTO> list(PageRequestDTO pageRequestDTO);
}
