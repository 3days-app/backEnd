package com.hongsi.mapleton.service;

import com.hongsi.mapleton.entity.User;
import com.hongsi.mapleton.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired private final UserRepo userRepo;

    //회원가입
    @Transactional
    public Long signUp(User user) {
        userRepo.save(user);
        return user.getId();
    }
}
