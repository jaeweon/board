package com.board.board.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private String userId;
    private String nickName;
    private boolean isAdmin;
    private boolean isWithdraw;
}
