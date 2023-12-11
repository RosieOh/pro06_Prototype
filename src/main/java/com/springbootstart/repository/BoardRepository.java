package com.springbootstart.repository;

import com.springbootstart.entity.Board;
import com.springbootstart.service.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    List<Board> findByBno(Long bno, String boardType);

}
