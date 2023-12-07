package com.springbootstart.repository;

import com.springbootstart.entity.StudentEntranceBoard;
import com.springbootstart.service.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentEntranceBoardRepository extends JpaRepository<StudentEntranceBoard, Long>, BoardSearch {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
