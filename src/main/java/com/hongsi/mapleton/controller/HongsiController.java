package com.hongsi.mapleton.controller;

import com.hongsi.mapleton.dto.*;
import com.hongsi.mapleton.service.HongsiService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/hong-si")
@RestController
@RequiredArgsConstructor
public class HongsiController {
    private final HongsiService hongsiService;

    /**
     * 홍시 리스트 출력
     */
    @GetMapping("")
    public List<HongsiListDto> getHongsiList() {
        return hongsiService.listHongsi();
    }

    /**
     * 홍시 디테일 출력
     */
    @GetMapping("/{hongsi_id}")
    public HongsiDetailDto getHongsiDetail(@PathVariable(name = "hongsi_id") Long hongsiId){
            return hongsiService.hongsiDetail(hongsiId);
    }

    /**
     * 홍시 보드 리스트 출력
     */
    @GetMapping("/board/{hongsi_id}")
    public List<HongsiBoardDto> getHongsiBoardList(@PathVariable(name = "hongsi_id") Long hongsiId) {
        return hongsiService.hongsiBoardList(hongsiId);
    }

    /**
     * 목표 작성하기
     */
    @PostMapping("")
    public ResponseEntity postHongsi(HttpServletRequest request,
                                     @RequestBody RequestDto requestDto){

        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

        if (sessionUser == null) {
//            resultDto.setResultCode("fail");
//            resultDto.setResultMessage("유효하지 않은 요청입니다");
        } else {
            requestDto.setUser_id(sessionUser.getUser_id());
            return hongsiService.writeHongsi(requestDto);
//            resultDto.setResultCode("success");
//            resultDto.setResultMessage("내가 만든 홍시 조회 성공");
        }

        return hongsiService.writeHongsi(requestDto);
    }

    /**
     * 목표 참여하기
     */
    @PostMapping("/join/{hongsi_id}")
    public ResponseEntity postParticipateHongsi(HttpServletRequest request,
                                                @PathVariable(name = "hongsi_id") Long hongsiId) {

        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

        return  hongsiService.participateHongsi(sessionUser.getUser_id(), hongsiId);
    }

    /**
     * 목표 삭제하기
     */
    @DeleteMapping("")
    public ResponseEntity deleteHongsi(@RequestBody RequestDto requestDto){
        return hongsiService.deleteHongsi(requestDto.getHongsi_id());
    }
}
