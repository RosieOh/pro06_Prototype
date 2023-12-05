package com.springbootstart.repository;

import com.springbootstart.domain.StudentCTLBoard;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentCTLBoardRepository extends JpaRepository<StudentCTLBoard, Long> {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
