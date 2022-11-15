package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.UserConHongsi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConHongsiRepo extends JpaRepository<UserConHongsi, Long> {

    public List<UserConHongsi> findByUserId(Long userId);

}
