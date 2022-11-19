package com.hongsi.mapleton.controller;

import com.hongsi.mapleton.dto.*;
import com.hongsi.mapleton.service.HongsiService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
     * 홍시 보드 작성하기
     */
    @PostMapping("/board/{hongsi_id}")
    public ResponseEntity postHongsiBoard(@PathVariable(name = "hongsi_id") Long hongsiId,
                                          @RequestPart(name = "content") String content,
                                          @RequestPart(name = "image") MultipartFile multipartFile) throws IOException {
        return hongsiService.writeHongsiBoard(hongsiId, content , multipartFile);
    }

    /**
     * 목표 작성하기
     */
    @PostMapping("")
    public ResponseEntity postHongsi(HttpServletRequest request,
                                     @RequestPart(name = "requestDto") RequestDto requestDto,
                                     @RequestPart(name = "image") MultipartFile multipartFile) throws IOException {

        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");


        return hongsiService.writeHongsi(sessionUser.getUser_id(),requestDto, multipartFile);
    }

    /**
     * 목표 참여하기
     */
    @PostMapping("/join/{hongsi_id}")
    public ResponseEntity postParticipateHongsi(HttpServletRequest request,
                                                @PathVariable(name = "hongsi_id") Long hongsiId,
                                                @RequestBody RequestDto requestDto) {

        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

//        return  hongsiService.participateHongsi(sessionUser.getUser_id(), hongsiId);
        return hongsiService.participateHongsi(requestDto.getUser_id(), hongsiId);
    }

    /**
     * 목표 삭제하기
     */
    @DeleteMapping("")
    public ResponseEntity deleteHongsi(@RequestBody RequestDto requestDto){
        return hongsiService.deleteHongsi(requestDto.getHongsi_id());
    }

    /**
     * 홍시 카테고리 필터링
     */
    @PostMapping("/category")
    public List<HongsiListDto> getCategoryHongsi(@RequestBody RequestDto requestDto){
        return  hongsiService.categoryHongsiList(requestDto.getCategory());
    }
}
