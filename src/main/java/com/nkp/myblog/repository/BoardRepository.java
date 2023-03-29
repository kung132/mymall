package com.nkp.myblog.repository;

import com.nkp.myblog.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Page<Board>> findByTitleContaining(String title, Pageable pageable);
}
