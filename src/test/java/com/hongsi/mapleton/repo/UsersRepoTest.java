package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.Users;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class UsersRepoTest {

    @Autowired UserRepo userRepo;

    @Test
    @Rollback(value = false)
    void findByEmailAndPassword() {
        Users users = new Users("email", "password", "nickname");
        Users save = userRepo.save(users);
        System.out.println("save = " + save);
        Users byEmailAndPassword = userRepo.findByEmailAndPassword("email", "password").get();
        Assertions.assertThat(users).isEqualTo(byEmailAndPassword);
    }
}