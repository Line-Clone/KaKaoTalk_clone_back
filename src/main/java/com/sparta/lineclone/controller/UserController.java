package com.sparta.lineclone.controller;

import com.sparta.lineclone.dto.*;
import com.sparta.lineclone.entity.Friend;
import com.sparta.lineclone.dto.LoginRequestDto;
import com.sparta.lineclone.dto.MsgResponseDto;
import com.sparta.lineclone.dto.SignupRequestDto;
import com.sparta.lineclone.dto.UserInfo;
import com.sparta.lineclone.security.UserDetailsImpl;
import com.sparta.lineclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<MsgResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {   //(@RequestBody @Valid SignupRequestDto signupRequestDto)?
        userService.signup(signupRequestDto);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", HttpStatus.OK.value()));
    }

//    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<MsgResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok(new MsgResponseDto("로그인 완료",HttpStatus.OK.value()));
    }

    @PostMapping("/friend/search")
    public ResponseEntity<UserInfo> searchFriend(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam String friendName){
        return ResponseEntity.ok(userService.searchFriend(userDetails.getUser(),friendName));
    }
    @PostMapping("/addfriend/{friendId}")
    public ResponseEntity<MsgResponseDto> addFriend(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long friendId){
        userService.addFriend(userDetails.getUser(), friendId);

        return ResponseEntity.ok(new MsgResponseDto("친구 추가 완료",HttpStatus.OK.value()));
    }
}