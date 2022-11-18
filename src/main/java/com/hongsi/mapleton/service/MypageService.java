package com.hongsi.mapleton.service;

import com.hongsi.mapleton.entity.Hongsi;
import com.hongsi.mapleton.entity.Users;
import com.hongsi.mapleton.entity.UserConHongsi;
import com.hongsi.mapleton.repo.HongsiRepo;
import com.hongsi.mapleton.repo.UserConHongsiRepo;
import com.hongsi.mapleton.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class MypageService {

    private final UserRepo userRepo;
    private final HongsiRepo hongsiRepo;
    private final UserConHongsiRepo userConHongsiRepo;

    public List<Hongsi> findOwnerHongsi(Long userId) {
        Users findUsers = userRepo.findById(userId).get();
        List<UserConHongsi> findUserConHongsi = userConHongsiRepo.findByUsersId(userId);
        List<Hongsi> result = new ArrayList<>();
        for (UserConHongsi userConHongsi : findUserConHongsi ) {
            Hongsi findHongsi = hongsiRepo.findById(userConHongsi.getHongsi().getId()).get();
            if (Objects.equals(findHongsi.getWriter(), findUsers.getNickname())) {
                result.add(findHongsi);
            }
        }
        return result;
    }

    public List<Hongsi> findParticipateHongsi(Long userId) {
        Users findUsers = userRepo.findById(userId).get();
        List<UserConHongsi> findUserConHongsi = userConHongsiRepo.findByUsersId(userId);
        List<Hongsi> result = new ArrayList<>();
        for (UserConHongsi userConHongsi : findUserConHongsi) {
            if (Objects.equals(userConHongsi.getUsersId(), findUsers)) {
                result.add(userConHongsi.getHongsi());
            }
        }
        return result;
    }

    public List<Hongsi> findCompleteHongsi(Long userId) {
        Users findUsers = userRepo.findById(userId).get();
        List<UserConHongsi> findUserConHongsi = userConHongsiRepo.findByUsersId(findUsers.getId());
        List<Hongsi> result = new ArrayList<>();
        for (UserConHongsi userConHongsi : findUserConHongsi ) {
            Hongsi findHongsi = hongsiRepo.findById(userConHongsi.getHongsi().getId()).get();
            if (Objects.equals(findHongsi.getStatus(), "complete")) {
                result.add(findHongsi);
            }
        }
        return result;
    }

}
