package com.board.board.request;

import lombok.Getter;

@Getter
public class UserUpdateRequest {
    private String userId;
    private String beforePassword;
    private String afterPassword;
}
