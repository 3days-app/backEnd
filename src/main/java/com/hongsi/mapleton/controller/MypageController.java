package com.hongsi.mapleton.controller;


import com.hongsi.mapleton.dto.HongsiDto;
import com.hongsi.mapleton.dto.ResultDto;
import com.hongsi.mapleton.dto.UserDto;
import com.hongsi.mapleton.entity.Hongsi;
import com.hongsi.mapleton.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/mypage/hong-si/owner")
    public ResultDto findOwnerHongsi(HttpServletRequest request,
                                           @RequestParam Long userId) {

        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

        ResultDto resultDto = new ResultDto();

        if (sessionUser == null) {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage("유효하지 않은 요청입니다");
        } else {
            resultDto.setResultCode("success");
            resultDto.setResultMessage("조회 성공");
        }

        List<HongsiDto> result = new ArrayList<>();
        for (Hongsi hongsi : mypageService.findOwnerHongsi(userId)) {
            HongsiDto hongsiDto = new HongsiDto();
            hongsiDto.title = hongsi.getTitle();
            hongsiDto.startDate = hongsi.getStartDate();
            hongsiDto.endDate = hongsi.getEndDate();
            result.add(hongsiDto);
        }

        resultDto.setData(result);
        return resultDto;
    }

    @GetMapping("/mypage/hong-si/participant")
    public List<HongsiDto> findParticipateHongsi(@RequestParam(value="user_id") Long userId) {
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
    public List<HongsiDto> findCompletedHongsi(@RequestParam(value="user_id") Long userId) {
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
