package com.springbootstart.repository.search.board;

import com.springbootstart.domain.Board;
import com.springbootstart.domain.StudentCTLBoard;
import com.springbootstart.domain.TeacherCTLBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {

    Page<Board> search1(Pageable pageable);

    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<StudentCTLBoard> search2(Pageable pageable);

    Page<StudentCTLBoard> searchAll2(String[] types, String keyword, Pageable pageable);

    Page<TeacherCTLBoard> search3(Pageable pageable);

    Page<TeacherCTLBoard> searchAll3(String[] types, String keyword, Pageable pageable);
}
