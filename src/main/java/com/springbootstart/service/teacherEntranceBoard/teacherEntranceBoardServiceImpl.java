package com.springbootstart.service.teacherEntranceBoard;

import com.springbootstart.entity.TeacherEntranceBoard;
import com.springbootstart.dto.TeacherEntranceBoardDTO;
import com.springbootstart.dto.PageRequestDTO;
import com.springbootstart.dto.PageResponseDTO;
import com.springbootstart.repository.TeacherEntranceBoardRepository;
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
public class teacherEntranceBoardServiceImpl implements teacherEntranceBoardService {

    private final ModelMapper modelMapper;
    private final TeacherEntranceBoardRepository teacherEntranceBoardRepository;

    @Override
    public Long register(TeacherEntranceBoardDTO teacherEntranceBoardDTO) {
        TeacherEntranceBoard teacherEntranceBoard = modelMapper.map(teacherEntranceBoardDTO, TeacherEntranceBoard.class);
        Long tebno = teacherEntranceBoardRepository.save(teacherEntranceBoard).getTebno();
        return tebno;
    }

    @Override
    public TeacherEntranceBoardDTO readOne(Long tebno) {
        Optional<TeacherEntranceBoard> result = teacherEntranceBoardRepository.findById(tebno);
        TeacherEntranceBoard teacherEntranceBoard = result.orElseThrow();
        TeacherEntranceBoardDTO teacherEntranceBoardDTO = modelMapper.map(teacherEntranceBoard, TeacherEntranceBoardDTO.class);
        return teacherEntranceBoardDTO;
    }

    @Override
    public void modify(TeacherEntranceBoardDTO teacherEntranceBoardDTO) {
        Optional<TeacherEntranceBoard> result = teacherEntranceBoardRepository.findById(teacherEntranceBoardDTO.getTebno());
        TeacherEntranceBoard teacherEntranceBoard = result.orElseThrow();
        teacherEntranceBoard.change(teacherEntranceBoard.getTitle(), teacherEntranceBoard.getContent());
        teacherEntranceBoardRepository.save(teacherEntranceBoard);
    }

    @Override
    public void remove(Long tebno) {
        teacherEntranceBoardRepository.deleteById(tebno);
    }

    @Override
    public PageResponseDTO<TeacherEntranceBoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("tebno");

        Page<TeacherEntranceBoard> result = teacherEntranceBoardRepository.searchAll5(types, keyword, pageable);

        List<TeacherEntranceBoardDTO> dtoList = result.getContent().stream()
                .map(teacherEntranceBoard -> modelMapper.map(teacherEntranceBoard, TeacherEntranceBoardDTO.class))
                .collect(Collectors.toList());
        return PageResponseDTO.<TeacherEntranceBoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

}
