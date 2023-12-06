package com.springbootstart.repository;

import com.springbootstart.domain.TeacherCTLBoard;
import com.springbootstart.service.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherCTLBoardRepository extends JpaRepository<TeacherCTLBoard, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
