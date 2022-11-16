package com.hongsi.mapleton.service;

import com.hongsi.mapleton.dto.UserDto;
import com.hongsi.mapleton.entity.User;
import com.hongsi.mapleton.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    @Transactional
    public User createUser(UserDto userDto) {

        validateDuplicateUser(userDto); // 중복 사용자 검증

        User user = new User(
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getNickname()
        );
        userRepo.save(user);
        return user;
    }


    @Transactional
    public void updateUser(UserDto userDto) {

        User user = userRepo.findById(userDto.getUser_id()).orElse(null);
        if (user == null) {
            throw new IllegalStateException("사용자가 존재하지 않습니다.");
        }

        user.setNickname(userDto.getNickname());
    }


    @Transactional
    public void removeUser(UserDto userDto) {

        User user = userRepo.findById(userDto.getUser_id()).orElse(null);
        if (user == null) {
            throw new IllegalStateException("사용자가 존재하지 않습니다.");
        }

        userRepo.delete(user);
    }

    private void validateDuplicateUser(UserDto userDto) {
        User findUser = userRepo.findByEmail(userDto.getEmail());
        if (findUser != null) {
            throw new IllegalStateException("중복 사용자가 있습니다.");
        }
    }
}
