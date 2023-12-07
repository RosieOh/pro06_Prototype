package com.springbootstart.service.search;

import com.springbootstart.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<StudentCTLBoard> search2(Pageable pageable);

    Page<StudentCTLBoard> searchAll2(String[] types, String keyword, Pageable pageable);

    Page<TeacherCTLBoard> search3(Pageable pageable);

    Page<TeacherCTLBoard> searchAll3(String[] types, String keyword, Pageable pageable);

    Page<StudentEntranceBoard> search4(Pageable pageable);

    Page<StudentEntranceBoard> searchAll4(String[] types, String keyword, Pageable pageable);

    Page<TeacherEntranceBoard> search5(Pageable pageable);

    Page<TeacherEntranceBoard> searchAll5(String[] types, String ketword, Pageable pageable);
}