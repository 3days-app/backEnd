package com.hongsi.mapleton.service;

import com.hongsi.mapleton.dto.UserDto;
import com.hongsi.mapleton.entity.Users;
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
    public Users createUser(UserDto userDto) {

        validateDuplicateUser(userDto); // 중복 사용자 검증

        Users users = new Users(
                userDto.getEmail(),
                userDto.getPassword(),
                userDto.getNickname()
        );
        userRepo.save(users);
        return users;
    }


    @Transactional
    public void updateUser(UserDto userDto) {

        Users users = userRepo.findById(userDto.getUser_id()).orElse(null);
        if (users == null) {
            throw new IllegalStateException("사용자가 존재하지 않습니다.");
        }

        users.setNickname(userDto.getNickname());
    }


    @Transactional
    public void removeUser(UserDto userDto) {

        Users users = userRepo.findById(userDto.getUser_id()).orElse(null);
        if (users == null) {
            throw new IllegalStateException("사용자가 존재하지 않습니다.");
        }

        userRepo.delete(users);
    }

    private void validateDuplicateUser(UserDto userDto) {
        Users findUsers = userRepo.findByEmail(userDto.getEmail());
        if (findUsers != null) {
            throw new IllegalStateException("중복 사용자가 있습니다.");
        }
    }
}
