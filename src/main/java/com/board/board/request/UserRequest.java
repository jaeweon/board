package com.board.board.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String userId;
    private String password;
    private String nickName;
    private boolean isAdmin;
    private boolean isWithdraw;
}
