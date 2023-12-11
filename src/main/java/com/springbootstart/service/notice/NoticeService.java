package com.springbootstart.service.notice;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;

import java.util.List;

public interface NoticeService {

    public BoardDTO findByBno(Long bno);
    public List<BoardDTO> findAll();
    public Long register(BoardDTO boardDTO);
    public void modify(BoardDTO boardDTO);
    public void remove(Long bno);

    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
