package com.springbootstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherCTLBoardRepository extends JpaRepository<StudentCTLBoardRepository, Long> {

    @Query(value = "select now()", nativeQuery = true)
    String getTime();
}
