package com.board.board.controller;

import com.board.board.aop.LoginCheck;
import com.board.board.dto.UserDTO;
import com.board.board.request.UserDeleteRequest;
import com.board.board.request.UserLoginRequest;
import com.board.board.request.UserRequest;
import com.board.board.request.UserUpdateRequest;
import com.board.board.response.LoginResponse;
import com.board.board.response.UserInfoResponse;
import com.board.board.response.UserResponse;
import com.board.board.service.UserService;
import com.board.board.util.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.register(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest loginRequest, HttpSession session) {
        // 서비스 계층에서 로그인 처리 및 유저 정보 반환
        UserResponse userInfo = userService.login(loginRequest.getUserId(), loginRequest.getPassword());

        // 세션 설정
        String userId = userInfo.getUserId();
        if (userInfo.getStatus() == UserResponse.Status.ADMIN) {
            SessionUtil.setLoginAdminId(session, userId);
        } else {
            SessionUtil.setLoginMemberId(session, userId);
        }

        // 로그인 성공 응답 반환
        LoginResponse loginResponse = LoginResponse.success(userInfo);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/userInfo")
    public UserInfoResponse getUserInfo(HttpSession session) {
        String id = SessionUtil.getLoginMemberId(session);
        if (id == null) id = SessionUtil.getLoginAdminId(session);
        UserDTO userInfo = userService.getUserInfo(id);
        return new UserInfoResponse(userInfo);
    }

    @PutMapping("/updatePassword")
    @LoginCheck(type = LoginCheck.UserType.USER)
    public ResponseEntity<UserResponse> updatePassword(String accountId, @RequestBody UserUpdateRequest userRequest) {
        UserResponse response = userService.updatePassword(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/deleteUser")
    public void deleteUser(@RequestBody UserDeleteRequest userRequest) {
        userService.deletedId(userRequest);
    }

    @PutMapping("/logout")
    public void logout(HttpSession session) {
        SessionUtil.clear(session);
    }

}
