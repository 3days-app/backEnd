package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<Board, Long> {
}
