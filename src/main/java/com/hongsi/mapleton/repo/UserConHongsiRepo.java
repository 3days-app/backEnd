package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.Hongsi;
import com.hongsi.mapleton.entity.User;
import com.hongsi.mapleton.entity.UserConHongsi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConHongsiRepo extends JpaRepository<UserConHongsi, Long> {

    List<UserConHongsi> findByUserId(Long userId);
    UserConHongsi findByUserIdAndHongsiId(User user, Hongsi hongsi);
    List<UserConHongsi> findByHongsiId(Hongsi hongsi);
}
