package com.springbootstart.repository;

import com.springbootstart.entity.Board;
import com.springbootstart.service.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    @Query("SELECT b FROM Board b WHERE b.bno = :bno AND b.boardType = :boardType")
    Optional<Board> findById(@Param("bno") Long bno, @Param("boardType") String boardType);

    @Query("SELECT b, m.mname as writer FROM Board b LEFT JOIN b.member m WHERE b.bno = :bno AND b.boardType = :boardType")
    Optional<Object[]> findByIdAndBoardType(@Param("bno") Long bno, @Param("boardType") String boardType);

    @Query("SELECT b FROM Board b WHERE " +
            "(:keyword is null or b.title like %:keyword% or b.content like %:keyword%) " +
            "AND (:types is null or b.boardType in :types)")
    Page<Board> searchAll(@Param("types") String[] types,
                          @Param("keyword") String keyword,
                          Pageable pageable);

    // 새로운 메서드 추가
    Page<Board> findByBoardType(String boardType, Pageable pageable);
}
