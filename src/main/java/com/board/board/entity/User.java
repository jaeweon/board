package com.board.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String userId;

    @Column(nullable = false, length = 250)
    private String password;

    @Column(nullable = false, length = 50)
    private String nickName;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private boolean isWithdraw;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @Builder
    public User(String userId, String password, String nickName, boolean isAdmin, boolean isWithdraw) {
        this.userId = userId;
        this.password = password;
        this.nickName = nickName;
        this.isAdmin = isAdmin;
        this.isWithdraw = isWithdraw;
        this.createdAt = LocalDateTime.now();
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}