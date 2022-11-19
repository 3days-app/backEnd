package com.hongsi.mapleton.service;

import com.hongsi.mapleton.dto.HongsiBoardDto;
import com.hongsi.mapleton.dto.HongsiDetailDto;
import com.hongsi.mapleton.dto.HongsiListDto;
import com.hongsi.mapleton.dto.RequestDto;
import com.hongsi.mapleton.entity.Board;
import com.hongsi.mapleton.entity.Hongsi;
import com.hongsi.mapleton.entity.Users;
import com.hongsi.mapleton.entity.UserConHongsi;
import com.hongsi.mapleton.repo.BoardRepo;
import com.hongsi.mapleton.repo.HongsiRepo;
import com.hongsi.mapleton.repo.UserConHongsiRepo;
import com.hongsi.mapleton.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HongsiService {

    private final UserRepo userRepo;
    private final HongsiRepo hongsiRepo;
    private final UserConHongsiRepo userConHongsiRepo;
    private final BoardRepo boardRepo;
    private final S3Uploader s3Uploader;

    /**
     * 홍시리스트 출력
     */
    public List<HongsiListDto> listHongsi() {
        List<HongsiListDto> hongsiListDtoList = new ArrayList<>();
        List<Hongsi> hongsiList = hongsiRepo.findAll();

        for(Hongsi hongsi : hongsiList){
            HongsiListDto hongsiListDto = new HongsiListDto(hongsi);
            hongsiListDtoList.add(hongsiListDto);
        }

        return hongsiListDtoList;
    }

    /**
     * 홍시리스트 디테일 출력
     */
    public HongsiDetailDto hongsiDetail(Long hongsiId) {
        Hongsi hongsi = hongsiRepo.findById(hongsiId)
                .orElseThrow(() -> new IllegalStateException("없는 목표입니다."));

        return new HongsiDetailDto(hongsi);
    }

    /**
     * 홍시 보드 출력
     */
    public List<HongsiBoardDto> hongsiBoardList(Long hongsiId) {
        Hongsi hongsi = hongsiRepo.findById(hongsiId)
                .orElseThrow(()->new IllegalStateException("없는 목표입니다."));
        List<Board> boardList = boardRepo.findByHongsi(hongsi);
        List<HongsiBoardDto> hongsiBoardDtoList = new ArrayList<>();

        for(Board board : boardList) {
            HongsiBoardDto hongsiBoardDto = new HongsiBoardDto(board);

            hongsiBoardDtoList.add(hongsiBoardDto);
        }

        return hongsiBoardDtoList;
    }

    /**
     * 목표 게시판 작성하기 - S3 추가해야함
     */
    public ResponseEntity writeHongsiBoard(Long userId,Long hongsiId, String content , MultipartFile multipartFile) throws IOException {
        //values[0] = deleteImage , values[1] = Image;
        Hongsi hongsi = hongsiRepo.findById(hongsiId)
                .orElseThrow(() -> new IllegalStateException("홍시 없음"));
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("유저 없음"));
        Board board = new Board(hongsi);
        String values[] = s3Uploader.upload(multipartFile, "board");
        UserConHongsi userConHongsi = userConHongsiRepo.findByUsersIdAndHongsi(user, hongsi);

        userConHongsi.setCount(userConHongsi.getCount()+1);
        if(userConHongsi.getCount() >= 3)
            userConHongsi.setStatus("completed");

        board.setContent(content);
        board.setDeleteImage(values[0]);
        board.setImage(values[1]);
        boardRepo.save(board);

        return new ResponseEntity("게시글 작성 완료", HttpStatus.OK);
    }

    /**
     * 목표 작성하기
     */
    public ResponseEntity writeHongsi(Long userId,RequestDto requestDto, MultipartFile multipartFile) throws IOException{
        String values[] = s3Uploader.upload(multipartFile, "hong-si");

        Hongsi hongsi = new Hongsi(requestDto);
        hongsi.setDeleteImage(values[0]);
        hongsi.setImage(values[1]);
        hongsiRepo.save(hongsi);
        Users users = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("없는 유저"));
        UserConHongsi userConHongsi = new UserConHongsi(users, hongsi);
        userConHongsi.setStatus("running");
        userConHongsi.setCount(0l);
        userConHongsiRepo.save(userConHongsi);
        return new ResponseEntity("목표 작성 완료", HttpStatus.OK);
    }

    /**
     * 목표 참여하기
     */
    public ResponseEntity participateHongsi(Long userId, Long hongsiId) {
        Users users = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalStateException("유저 없음"));
        Hongsi hongsi = hongsiRepo.findById(hongsiId)
                .orElseThrow(() -> new IllegalStateException("목표 없음"));

        UserConHongsi userConHongsiCheck = userConHongsiRepo.findByUsersIdAndHongsi(users, hongsi);

        System.out.println(userConHongsiCheck);
        if(hongsi.getCurrentParticipant() >= hongsi.getMaxParticipant()){
            return new ResponseEntity("참여 인원 초과했습니다. 다른 홍시에 참여해주세요...", HttpStatus.FORBIDDEN);
        }
        if(userConHongsiCheck != null) {
            return new ResponseEntity("이미 참여한 홍시입니다. 다른 홍시를 참여해주세요...", HttpStatus.FORBIDDEN);
        }

        UserConHongsi userConHongsi = new UserConHongsi(users, hongsi);
        userConHongsiRepo.save(userConHongsi);
        hongsi.setCurrentParticipant(hongsi.getCurrentParticipant() + 1);

        return new ResponseEntity("목표 참여 완료", HttpStatus.OK);
    }

    /**
     * 목표 삭제하기
     */
    public ResponseEntity deleteHongsi(Long hongsiId) {
        Hongsi hongsi = hongsiRepo.findById(hongsiId)
                .orElseThrow(() -> new IllegalStateException("홍시 없음"));
        List<UserConHongsi> userConHongsiList = userConHongsiRepo.findByHongsi(hongsi);

        for(UserConHongsi userConHongsi : userConHongsiList){
            userConHongsiRepo.delete(userConHongsi);
        }

        hongsiRepo.deleteById(hongsiId);

        return new ResponseEntity("목표 삭제 완료", HttpStatus.OK);
    }

    /**
     * 카테고리 필터링
     */
    public List<HongsiListDto> categoryHongsiList(String category) {
        List<Hongsi> hongsiList = hongsiRepo.findByCategory(category);
        List<HongsiListDto> hongsiListDtoList = new ArrayList<>();

        for(Hongsi hongsi : hongsiList){
            HongsiListDto hongsiListDto = new HongsiListDto(hongsi);
            hongsiListDtoList.add(hongsiListDto);
        }

        return hongsiListDtoList;
    }
}
