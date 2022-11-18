package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long> {
    Optional<Users> findByEmailAndPassword(String email, String password);

    Users findByEmail(String email);

}
