package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.Hongsi;
import com.hongsi.mapleton.entity.Users;
import com.hongsi.mapleton.entity.UserConHongsi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserConHongsiRepo extends JpaRepository<UserConHongsi, Long> {


    List<UserConHongsi> findByUsersId(Users users);
    UserConHongsi findByUsersIdAndHongsi(Users users, Hongsi hongsi);
    List<UserConHongsi> findByHongsi(Hongsi hongsi);
}
