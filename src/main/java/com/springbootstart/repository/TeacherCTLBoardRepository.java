package com.springbootstart.repository;

import com.springbootstart.domain.TeacherCTLBoard;
import com.springbootstart.repository.search.board.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherCTLBoardRepository extends JpaRepository<TeacherCTLBoard, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
