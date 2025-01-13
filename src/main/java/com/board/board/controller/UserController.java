package com.board.board.controller;

import com.board.board.request.UserRequest;
import com.board.board.request.UserUpdateRequest;
import com.board.board.response.UserResponse;
import com.board.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        UserResponse responseDTO = userService.register(userRequest);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestParam String userId, @RequestParam String password) {
        UserResponse response = userService.login(userId, password);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/updatePassword")
    public ResponseEntity<UserResponse> updatePassword(@RequestBody UserUpdateRequest userRequest) {
        UserResponse responseDTO = userService.updatePassword(userRequest);
        return ResponseEntity.ok(responseDTO);
    }
}
