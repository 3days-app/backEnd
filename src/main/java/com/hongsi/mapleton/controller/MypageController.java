package com.hongsi.mapleton.controller;


import com.hongsi.mapleton.dto.HongsiDto;
import com.hongsi.mapleton.entity.Hongsi;
import com.hongsi.mapleton.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/mypage/hong-si/owner")
    public List<HongsiDto> findOwnerHongsiDto(@RequestParam(value="user_id") Long userId) {
        List<HongsiDto> result = new ArrayList<>();
        for (Hongsi hongsi : mypageService.findOwnerHongsi(userId)) {
            HongsiDto hongsiDto = new HongsiDto();
            hongsiDto.title = hongsi.getTitle();
            hongsiDto.startDate = hongsi.getStartDate();
            hongsiDto.endDate = hongsi.getEndDate();
            result.add(hongsiDto);
        }
        return result;
    }

    @GetMapping("/mypage/hong-si/participant")
    public List<HongsiDto> findParticipateHongsiDto(@RequestParam(value="user_id") Long userId) {
        List<HongsiDto> result = new ArrayList<>();
        for (Hongsi hongsi : mypageService.findParticipateHongsi(userId)) {
            HongsiDto hongsiDto = new HongsiDto();
            hongsiDto.title = hongsi.getTitle();
            hongsiDto.startDate = hongsi.getStartDate();
            hongsiDto.endDate = hongsi.getEndDate();
            result.add(hongsiDto);
        }
        return result;
    }

    @GetMapping("/mypage/hong-si/completed")
    public List<HongsiDto> findCompletedHongsiDto(@RequestParam(value="user_id") Long userId) {
        List<HongsiDto> result = new ArrayList<>();
        for (Hongsi hongsi : mypageService.findCompleteHongsi(userId)) {
            HongsiDto hongsiDto = new HongsiDto();
            hongsiDto.title = hongsi.getTitle();
            hongsiDto.startDate = hongsi.getStartDate();
            hongsiDto.endDate = hongsi.getEndDate();
            result.add(hongsiDto);
        }
        return result;
    }
}
