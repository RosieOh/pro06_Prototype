package com.springbootstart.service.studentCTLBoard;

import com.springbootstart.entity.StudentCTLBoard;

import com.springbootstart.dto.StudentCTLBoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.repository.StudentCTLBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        Page<StudentCTLBoard> result = studentCTLBoardRepository.searchAll2(types, keyword, pageable);

        List<StudentCTLBoardDTO> dtoList = result.getContent().stream()
                .map(studentCTLBoard -> modelMapper.map(studentCTLBoard, StudentCTLBoardDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<StudentCTLBoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
