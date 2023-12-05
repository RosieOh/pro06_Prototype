package com.springbootstart.service.studentCTLBoard;

import com.springbootstart.domain.StudentCTLBoard;
import com.springbootstart.dto.ctlBoard.StudentCTLBoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;
import com.springbootstart.repository.StudentCTLBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class StudentCTLBoardServiceImpl implements StudentCTLBoardSevice{

    private final ModelMapper modelMapper;
    private final StudentCTLBoardRepository studentCTLBoardRepository;

    @Override
    public Long register(StudentCTLBoardDTO studentCTLBoardDTO) {
        StudentCTLBoard studentCTLBoard = modelMapper.map(studentCTLBoardDTO, StudentCTLBoard.class);
        Long sbno = studentCTLBoardRepository.save(studentCTLBoard).getSbno();
        return sbno;
    }

    @Override
    public StudentCTLBoardDTO readOne(Long sbno) {
        Optional<StudentCTLBoard> result = studentCTLBoardRepository.findById(sbno);
        StudentCTLBoard studentCTLBoard = result.orElseThrow();
        StudentCTLBoardDTO studentCTLBoardDTO = modelMapper.map(studentCTLBoard, StudentCTLBoardDTO.class);
        return studentCTLBoardDTO;
    }

    @Override
    public void modify(StudentCTLBoardDTO studentCTLBoardDTO) {
        Optional<StudentCTLBoard> result = studentCTLBoardRepository.findById(studentCTLBoardDTO.getSbno());
        StudentCTLBoard studentCTLBoard = result.orElseThrow();
        studentCTLBoard.change(studentCTLBoardDTO.getTitle(), studentCTLBoardDTO.getContent());
        studentCTLBoardRepository.save(studentCTLBoard);
    }

    @Override
    public void remove(Long sbno) {
        studentCTLBoardRepository.deleteById(sbno);
    }

    @Override
    public PageResponseDTO<StudentCTLBoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("sbno");

        Page<StudentCTLBoard> re
        return ;
    }

    // 등록 작업 처리
}
