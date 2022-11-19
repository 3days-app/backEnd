package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.Hongsi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HongsiRepo extends JpaRepository<Hongsi, Long> {

    List<Hongsi> findByWriter(String username);
    List<Hongsi> findByCategory(String category);

}
