package com.springbootstart.service.board;

import com.springbootstart.entity.Board;
import com.springbootstart.dto.BoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.entity.BoardType;
import com.springbootstart.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    private PageRequestDTO pageRequestDTO1;

    public void createAndSaveBoards() {
        List<Board> board = new ArrayList<>();

        Board noticeBoard = new Board();
        noticeBoard.setTitle("공지사항");
        noticeBoard.setContent("내용");
        noticeBoard.setBoardType(BoardType.NOTICE.toString());
        board.add(noticeBoard);

        Board studentCTL = new Board();
        studentCTL.setTitle("학생 포트폴리오 게시판");
        studentCTL.setContent("내용");
        studentCTL.setBoardType(BoardType.StudentCTL.toString());
        board.add(studentCTL);

        Board teacherCTL = new Board();
        teacherCTL.setTitle("선생님용 학생 E-PortFolio 게시판");
        teacherCTL.setContent("내용");
        teacherCTL.setBoardType(BoardType.TeacherCTL.toString());
        board.add(teacherCTL);

        Board qna = new Board();
        qna.setTitle("질의응답");
        qna.setContent("내용");
        qna.setBoardType(BoardType.Qna.toString());
        board.add(qna);

        // 생성한 게시판들을 저장
        boardRepository.saveAll(board);
    }


    // Notice 게시판 조건 줘서 불러오는 메소드
    @Override
    public PageResponseDTO<BoardDTO> listNotice(PageRequestDTO pageRequestDTO) {
        this.pageRequestDTO1 = pageRequestDTO;
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        String boardType = BoardType.NOTICE.toString(); // 추가: NOTICE 타입만 검색
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result;

        if (boardType != null) {
            result = boardRepository.findByBoardType(boardType, pageable);
        } else {
            result = boardRepository.searchAll(types, keyword, pageable);
        }

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    // StudentCTL 게시판 조건 줘서 불러오는 메소드
    @Override
    public PageResponseDTO<BoardDTO> listStudentCTL(PageRequestDTO pageRequestDTO) {
        this.pageRequestDTO1 = pageRequestDTO;
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        String boardType = BoardType.StudentCTL.toString(); // 추가: NOTICE 타입만 검색
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result;

        if (boardType != null) {
            result = boardRepository.findByBoardType(boardType, pageable);
        } else {
            result = boardRepository.searchAll(types, keyword, pageable);
        }

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    // StudentEntrance 게시판 조건 줘서 불러오는 메소드
    @Override
    public PageResponseDTO<BoardDTO> listStudentEntrance(PageRequestDTO pageRequestDTO) {
        this.pageRequestDTO1 = pageRequestDTO;
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        String boardType = BoardType.StudentEntrance.toString(); // 추가: NOTICE 타입만 검색
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result;

        if (boardType != null) {
            result = boardRepository.findByBoardType(boardType, pageable);
        } else {
            result = boardRepository.searchAll(types, keyword, pageable);
        }

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    // TeacherCTL 게시판 조건 줘서 불러오는 메소드
    @Override
    public PageResponseDTO<BoardDTO> listTeacherCTL(PageRequestDTO pageRequestDTO) {
        this.pageRequestDTO1 = pageRequestDTO;
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        String boardType = BoardType.TeacherCTL.toString(); // 추가: NOTICE 타입만 검색
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result;

        if (boardType != null) {
            result = boardRepository.findByBoardType(boardType, pageable);
        } else {
            result = boardRepository.searchAll(types, keyword, pageable);
        }

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    // TeacherEntrance 게시판 조건 줘서 불러오는 메소드
    @Override
    public PageResponseDTO<BoardDTO> listTeacherEntrance(PageRequestDTO pageRequestDTO) {
        this.pageRequestDTO1 = pageRequestDTO;
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        String boardType = BoardType.TeacherEntance.toString(); // 추가: NOTICE 타입만 검색
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result;

        if (boardType != null) {
            result = boardRepository.findByBoardType(boardType, pageable);
        } else {
            result = boardRepository.searchAll(types, keyword, pageable);
        }

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }
    // Faq 게시판 조건 줘서 불러오는 메소드
    @Override
    public PageResponseDTO<BoardDTO> listFaq(PageRequestDTO pageRequestDTO) {
        this.pageRequestDTO1 = pageRequestDTO;
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        String boardType = BoardType.Faq.toString(); // 추가: NOTICE 타입만 검색
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result;

        if (boardType != null) {
            result = boardRepository.findByBoardType(boardType, pageable);
        } else {
            result = boardRepository.searchAll(types, keyword, pageable);
        }

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    // Qna 게시판 조건 줘서 불러오는 메소드
    @Override
    public PageResponseDTO<BoardDTO> listQna(PageRequestDTO pageRequestDTO) {
        this.pageRequestDTO1 = pageRequestDTO;
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        String boardType = BoardType.Qna.toString(); // 추가: NOTICE 타입만 검색
        Pageable pageable = pageRequestDTO.getPageable("bno");

        Page<Board> result;

        if (boardType != null) {
            result = boardRepository.findByBoardType(boardType, pageable);
        } else {
            result = boardRepository.searchAll(types, keyword, pageable);
        }

        List<BoardDTO> dtoList = result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
    }

    @Override
    public BoardDTO findByBno(Long bno, String boardType) {
        Optional<Board> result = boardRepository.findById(bno, boardType);
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
}
