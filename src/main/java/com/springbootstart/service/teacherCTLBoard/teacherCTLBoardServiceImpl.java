package com.springbootstart.service.teacherCTLBoard;

import com.springbootstart.domain.TeacherCTLBoard;
import com.springbootstart.dto.ctlBoard.TeacherCTLBoardDTO;
import com.springbootstart.dto.page.PageRequestDTO;
import com.springbootstart.dto.page.PageResponseDTO;
import com.springbootstart.repository.TeacherCTLBoardRepository;
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
public class teacherCTLBoardServiceImpl implements teacherCTLBoardService{

    private final ModelMapper modelMapper;
    private final TeacherCTLBoardRepository teacherCTLBoardRepository;


    @Override
    public Long register(TeacherCTLBoardDTO teacherCTLBoardDTO) {
        TeacherCTLBoard teacherCTLBoard = modelMapper.map(teacherCTLBoardDTO, TeacherCTLBoard.class);
        Long tbno = teacherCTLBoardRepository.save(teacherCTLBoard).getTbno();
        return tbno;
    }

    @Override
    public TeacherCTLBoardDTO readOne(Long tbno) {
        Optional<TeacherCTLBoard> result = teacherCTLBoardRepository.findById(tbno);
        TeacherCTLBoard teacherCTLBoard = result.orElseThrow();
        TeacherCTLBoardDTO teacherCTLBoardDTO = modelMapper.map(teacherCTLBoard, TeacherCTLBoardDTO.class);
        return teacherCTLBoardDTO;
    }

    @Override
    public void modify(TeacherCTLBoardDTO teacherCTLBoardDTO) {
        Optional<TeacherCTLBoard> result = teacherCTLBoardRepository.findById(teacherCTLBoardDTO.getTbno());
        TeacherCTLBoard teacherCTLBoard = result.orElseThrow();
        teacherCTLBoard.change(teacherCTLBoard.getTitle(), teacherCTLBoardDTO.getContent());
        teacherCTLBoardRepository.save(teacherCTLBoard);
    }

    @Override
    public void remove(Long tbno) {
        teacherCTLBoardRepository.deleteById(tbno);
    }

    @Override
    public PageResponseDTO<TeacherCTLBoardDTO> list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("tbno");

        Page<TeacherCTLBoard> result = teacherCTLBoardRepository.searchAll3(types, keyword, pageable);

        List<TeacherCTLBoardDTO> dtoList = result.getContent().stream()
                .map(teacherCTLBoard -> modelMapper.map(teacherCTLBoard, TeacherCTLBoardDTO.class))
                .collect(Collectors.toList());
        return PageResponseDTO.<TeacherCTLBoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
