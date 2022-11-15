package com.hongsi.mapleton.service;

import com.hongsi.mapleton.entity.Hongsi;
import com.hongsi.mapleton.entity.User;
import com.hongsi.mapleton.entity.UserConHongsi;
import com.hongsi.mapleton.repo.HongsiRepo;
import com.hongsi.mapleton.repo.UserConHongsiRepo;
import com.hongsi.mapleton.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MypageService {

    private final UserRepo userRepo;
    private final HongsiRepo hongsiRepo;
    private final UserConHongsiRepo userConHongsiRepo;

    public List<Hongsi> findOwnerHongsi(Long userId) {
        User findUser = userRepo.findById(userId).get();
        List<UserConHongsi> findUserConHongsi = userConHongsiRepo.findByUserId(userId);
        List<Hongsi> result = new ArrayList<>();
        for (UserConHongsi userConHongsi : findUserConHongsi ) {
            Hongsi findHongsi = hongsiRepo.findById(userConHongsi.getHongsiId().getId()).get();
            if (findHongsi.getWriter() == findUser.getNickname()) {
                result.add(findHongsi);
            }
        }
        return result;
    }

    public List<Hongsi> findParticipateHongsi(Long userId) {
        User findUser = userRepo.findById(userId).get();
        List<UserConHongsi> findUserConHongsi = userConHongsiRepo.findByUserId(userId);
        List<Hongsi> result = new ArrayList<>();
        for (UserConHongsi userConHongsi : findUserConHongsi) {
            if (userConHongsi.getUserId() == findUser) {
                result.add(userConHongsi.getHongsiId());
            }
        }
        return result;
    }

    public List<Hongsi> findCompleteHongsi(Long userId) {
        User findUser = userRepo.findById(userId).get();
        List<UserConHongsi> findUserConHongsi = userConHongsiRepo.findByUserId(findUser.getId());
        List<Hongsi> result = new ArrayList<>();
        for (UserConHongsi userConHongsi : findUserConHongsi ) {
            Hongsi findHongsi = hongsiRepo.findById(userConHongsi.getHongsiId().getId()).get();
            if (findHongsi.getStatus() == "complete") {
                result.add(findHongsi);
            }
        }
        return result;
    }

}
