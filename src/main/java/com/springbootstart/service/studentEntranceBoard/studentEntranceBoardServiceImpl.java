package com.springbootstart.service.studentEntranceBoard;

import com.springbootstart.entity.StudentEntranceBoard;
import com.springbootstart.dto.entranceBoard.StudentEntranceBoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;
import com.springbootstart.repository.StudentEntranceBoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class studentEntranceBoardServiceImpl implements studentEntranceBoardService {

    private final ModelMapper modelMapper;
    private final StudentEntranceBoardRepository studentEntranceBoardRepository;

    @Override
    public Long register(StudentEntranceBoardDTO studentEntranceBoardDTO) {
        StudentEntranceBoard studentEntranceBoard = modelMapper.map(studentEntranceBoardDTO, StudentEntranceBoard.class);

        Long sebno = studentEntranceBoardRepository.save(studentEntranceBoard).getSebno();
        return sebno;
    }

    @Override
    public StudentEntranceBoardDTO readOne(Long sebno) {
        Optional<StudentEntranceBoard> result = studentEntranceBoardRepository.findById(sebno);
        StudentEntranceBoard studentEntranceBoard = result.orElseThrow();
        StudentEntranceBoardDTO studentEntranceBoardDTO = modelMapper.map(studentEntranceBoard, StudentEntranceBoardDTO.class);
        return studentEntranceBoardDTO;
    }

    @Override
    public void modify(StudentEntranceBoardDTO studentEntranceBoardDTO) {
        Optional<StudentEntranceBoard> result = studentEntranceBoardRepository.findById(studentEntranceBoardDTO.getSebno());
        StudentEntranceBoard studentEntranceBoard = result.orElseThrow();
        studentEntranceBoard.change(studentEntranceBoardDTO.getTitle(), studentEntranceBoardDTO.getContent());
        studentEntranceBoardRepository.save(studentEntranceBoard);
    }

    @Override
    public void remove(Long sebno) {
        studentEntranceBoardRepository.deleteById(sebno);
    }

    @Override
    public PageResponseDTO<StudentEntranceBoardDTO> list(PageRequestDTO pageRequestDTO) {

        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("sebno");

        Page<StudentEntranceBoard> result = studentEntranceBoardRepository.searchAll4(types, keyword, pageable);

        List<StudentEntranceBoardDTO> dtoList = result.getContent().stream()
                .map(studentEntranceBoard -> modelMapper.map(studentEntranceBoard, StudentEntranceBoardDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<StudentEntranceBoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
