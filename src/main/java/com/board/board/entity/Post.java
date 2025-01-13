package com.board.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private boolean isAdmin;

    @Column(nullable = false, length = 50)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Integer views;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @OneToMany(mappedBy = "post")
    private List<File> files;

    @ManyToMany
    @JoinTable(
            name = "postTag",
            joinColumns = @JoinColumn(name = "postId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private List<Tag> tags;

    @Builder
    public Post(String name, boolean isAdmin, String contents, LocalDateTime createdAt, Integer views, User user, Category category) {
        this.name = name;
        this.isAdmin = isAdmin;
        this.contents = contents;
        this.createdAt = createdAt;
        this.views = views;
        this.user = user;
        this.category = category;
    }
}