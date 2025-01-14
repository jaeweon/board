package com.board.board.response;

import com.board.board.dto.UserDTO;
import lombok.*;

@Getter
@Builder
public class LoginResponse {
    private boolean success;
    private String message;
    private UserResponse user;

    public static LoginResponse success(UserResponse user) {
        return LoginResponse.builder()
                .success(true)
                .message("Login successful")
                .user(user)
                .build();
    }

    public static LoginResponse failure(String message) {
        return LoginResponse.builder()
                .success(false)
                .message(message)
                .user(null)
                .build();
    }
}