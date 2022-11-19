package com.hongsi.mapleton.controller;

import com.hongsi.mapleton.dto.ResultDto;
import com.hongsi.mapleton.dto.UserDto;
import com.hongsi.mapleton.entity.Users;
import com.hongsi.mapleton.repo.UserRepo;
import com.hongsi.mapleton.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserRepo userRepo;
    private final UserService userService;

    @PostMapping(value = "/user/signup")
    public ResultDto signup(@RequestBody UserDto userDto) {

        ResultDto resultDto = new ResultDto();

        try {
            Users signupUsers = (Users) userService.createUser(userDto);

            if (signupUsers.getId() != null) {
                resultDto.setResultCode("success");
                resultDto.setResultMessage("사용자 생성 성공");
            } else {
                resultDto.setResultCode("fail");
                resultDto.setResultMessage("사용자 생성 실패");
            }

        } catch (IllegalStateException e) {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage(e.getMessage());
        }

        return resultDto;
    }

    @PostMapping("/user/login")
    public ResultDto login(HttpServletRequest request,
                      HttpServletResponse response,
                      @RequestBody UserDto userDto) {
        Optional<Users> loginUser = userRepo.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        ResultDto resultDto = new ResultDto();


        if (loginUser.isPresent()) {
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", userDto);
            userDto.setUser_id(loginUser.get().getId());
            userDto.setNickname(loginUser.get().getNickname());
            Cookie myCookie = new Cookie("cookieName", session.getId());
            myCookie.setSecure(false);
            myCookie.setMaxAge(100000000);
            myCookie.setPath("/"); // 모든 경로에서 접근 가능 하도록 설정
            resultDto.setResultCode("success");
            resultDto.setResultMessage("로그인 성공");
            resultDto.setData(userDto);
            response.addCookie(myCookie);
        } else {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage("로그인 실패");
        }
        return resultDto;
    }

    @PostMapping("/user/logout")
    public ResultDto logout(HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        ResultDto resultDto = new ResultDto();

        if (session != null) {
            session.invalidate();
            resultDto.setResultCode("success");
            resultDto.setResultMessage("로그아웃 성공");
        } else {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage("로그아웃할 사용자가 없습니다.");
        }
        return resultDto;
    }

    @DeleteMapping("/user")
    public ResultDto removeUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

        ResultDto resultDto = new ResultDto();

        if (sessionUser == null) {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage("유효하지 않은 요청입니다");
        } else {
            userService.removeUser(sessionUser.getUser_id());
            resultDto.setResultCode("success");
            resultDto.setResultMessage("사용자 삭제 성공");
        }

        return resultDto;
    }

    @PutMapping("/user/nickname")
    public ResultDto updateNickname(HttpServletRequest request,
                                    @RequestBody UserDto userDto) {
        HttpSession session = request.getSession();
        UserDto sessionUser = (UserDto) session.getAttribute("loginUser");

        ResultDto resultDto = new ResultDto();

        userDto.setUser_id(sessionUser.getUser_id());

        if (sessionUser == null) {
            resultDto.setResultCode("fail");
            resultDto.setResultMessage("유효하지 않은 요청입니다");
        } else {
            resultDto.setResultCode("success");
            resultDto.setResultMessage("닉네임 변경 성공");
            userService.updateUser(userDto);
        }

        resultDto.setData(userDto);
        return resultDto;
    }
}
