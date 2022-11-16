package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.Board;
import com.hongsi.mapleton.entity.Hongsi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepo extends JpaRepository<Board, Long> {
    List<Board> findByHongsi(Hongsi hongsi);
    void deleteByHongsiId(Hongsi hongsi);
}
