package com.springbootstart.service;

import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;

import java.util.List;

// 465P 부터 시작
public interface BoardService {

    public BoardDTO findByBno(Long bno, String boardType);
    public List<BoardDTO> findAll();
    public void register(BoardDTO boardDTO);
    public void modify(BoardDTO boardDTO);
    public void remove(Long bno);



    PageResponseDTO<BoardDTO> listNotice(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BoardDTO> listStudentCTL(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BoardDTO> listStudentEntrance(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BoardDTO> listTeacherCTL(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BoardDTO> listTeacherEntrance(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BoardDTO> listFaq(PageRequestDTO pageRequestDTO);
    PageResponseDTO<BoardDTO> listQna(PageRequestDTO pageRequestDTO);
}
