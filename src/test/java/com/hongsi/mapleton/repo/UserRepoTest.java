package com.hongsi.mapleton.repo;

import com.hongsi.mapleton.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.RequestParam;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepoTest {

    @Autowired UserRepo userRepo;

    @Test
    @Rollback(value = false)
    void findByEmailAndPassword() {
        User user = new User("email", "password", "nickname");
        User save = userRepo.save(user);
        System.out.println("save = " + save);
        User byEmailAndPassword = userRepo.findByEmailAndPassword("email", "password").get();
        Assertions.assertThat(user).isEqualTo(byEmailAndPassword);
    }
}