package com.springbootstart.repository;

import com.springbootstart.domain.StudentCTLBoard;
import com.springbootstart.service.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentCTLBoardRepository extends JpaRepository<StudentCTLBoard, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
