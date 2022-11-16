package com.hongsi.mapleton.controller;

import com.hongsi.mapleton.dto.HongsiBoardDto;
import com.hongsi.mapleton.dto.HongsiDetailDto;
import com.hongsi.mapleton.dto.HongsiListDto;
import com.hongsi.mapleton.dto.RequestDto;
import com.hongsi.mapleton.service.HongsiService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity postHongsi(@RequestBody RequestDto requestDto){
        return hongsiService.writeHongsi(requestDto);
    }

    /**
     * 목표 참여하기
     */
    @PostMapping("/join/{hongsi_id}")
    public ResponseEntity postParticipateHongsi(@PathVariable(name = "hongsi_id") Long hongsiId,
                                                @RequestBody RequestDto requestDto) {
        return  hongsiService.participateHongsi(requestDto.getUser_id(), hongsiId);
    }

    /**
     * 목표 삭제하기
     */
    @DeleteMapping("")
    public ResponseEntity deleteHongsi(@RequestBody RequestDto requestDto){
        return hongsiService.deleteHongsi(requestDto.getHongsi_id());
    }
}
