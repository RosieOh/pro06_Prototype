package com.springbootstart.repository;

import com.springbootstart.entity.TeacherEntranceBoard;
import com.springbootstart.service.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherEntranceBoardRepository extends JpaRepository<TeacherEntranceBoard, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
