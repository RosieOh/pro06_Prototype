package com.springbootstart.service.board;

import com.springbootstart.entity.Board;
import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService{

    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public BoardDTO findByBno(Long bno) {
        Optional<Board> result = boardRepository.findById(bno);
        BoardDTO dto = modelMapper.map(result, BoardDTO.class);
        return dto;
    }

    @Override
    public List<BoardDTO> findAll() {
        List<Board> lst = boardRepository.findAll();
        List<BoardDTO> boardList = lst.stream().map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());
        return boardList;
    }

    // 등록 작업 처리
    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = modelMapper.map(boardDTO, Board.class);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    // 수정 작업 처리
    @Override
    public void modify(BoardDTO boardDTO) {
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }

    // 삭제 작업 처리
    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
    }


    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
