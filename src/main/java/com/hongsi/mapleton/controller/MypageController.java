package com.hongsi.mapleton.controller;


import com.hongsi.mapleton.dto.HongsiDto;
import com.hongsi.mapleton.dto.ResultDto;
import com.hongsi.mapleton.dto.UserDto;
import com.hongsi.mapleton.entity.Hongsi;
import com.hongsi.mapleton.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @GetMapping("/mypage/hong-si/owner")
    public ResultDto findOwnerHongsi(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

        ResultDto resultDto = new ResultDto();

        if (sessionUser == null) {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage("유효하지 않은 요청입니다");
            return resultDto;
        } else {
            resultDto.setResultCode("success");
            resultDto.setResultMessage("내가 만든 홍시 조회 성공");
        }

        List<HongsiDto> result = new ArrayList<>();

        for (Hongsi hongsi : mypageService.findOwnerHongsi(sessionUser.getUser_id())) {
            HongsiDto hongsiDto = new HongsiDto();
            hongsiDto.setId(hongsi.getId());
            hongsiDto.setTitle(hongsi.getTitle());
            hongsiDto.setImgage(hongsi.getImage());
            hongsiDto.setStartDate(hongsi.getStartDate());
            hongsiDto.setEndDate(hongsi.getEndDate());

            result.add(hongsiDto);
        }

        resultDto.setData(result);
        return resultDto;
    }

    @GetMapping("/mypage/hong-si/participant")
    public ResultDto findParticipateHongsi(HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

        ResultDto resultDto = new ResultDto();

        if (sessionUser == null) {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage("유효하지 않은 요청입니다");
        } else {
            resultDto.setResultCode("success");
            resultDto.setResultMessage("내가 쫀 홍시 조회 성공");
        }

        List<HongsiDto> result = new ArrayList<>();
        for (Hongsi hongsi : mypageService.findParticipateHongsi(sessionUser.getUser_id())) {
            HongsiDto hongsiDto = new HongsiDto();
            hongsiDto.setId(hongsi.getId());
            hongsiDto.setTitle(hongsi.getTitle());
            hongsiDto.setImgage(hongsi.getImage());
            hongsiDto.setStartDate(hongsi.getStartDate());
            hongsiDto.setEndDate(hongsi.getEndDate());
            result.add(hongsiDto);
        }
        resultDto.setData(result);
        return resultDto;
    }

    @GetMapping("/mypage/hong-si/completed")
    public ResultDto findCompletedHongsi(HttpServletRequest request,
                                         @RequestParam(value = "user_id") Long userId) {

        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

        ResultDto resultDto = new ResultDto();

        if (sessionUser == null) {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage("유효하지 않은 요청입니다");
        } else {
            resultDto.setResultCode("success");
            resultDto.setResultMessage("내가 완료한 홍시 조회 성공");
        }

        List<HongsiDto> result = new ArrayList<>();
        for (Hongsi hongsi : mypageService.findCompleteHongsi(userId)) {
            HongsiDto hongsiDto = new HongsiDto();
            hongsiDto.setId(hongsi.getId());
            hongsiDto.setTitle(hongsi.getTitle());
            hongsiDto.setImgage(hongsi.getImage());
            hongsiDto.setStartDate(hongsi.getStartDate());
            hongsiDto.setEndDate(hongsi.getEndDate());
            result.add(hongsiDto);
        }
        resultDto.setData(result);
        return resultDto;
    }
}
