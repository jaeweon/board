package com.board.board.dto;

import ch.qos.logback.core.status.Status;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class UserDTO {

    public enum Status {
        DEFAULT,
        ADMIN,
        DELETED,
    }

    private int id;
    private String userId;
    private String password;
    private String nickName;
    private boolean isAdmin;
    private Date createdAt;
    private boolean isWithdraw;
    private Status status;
    private Date updatedAt;
}
