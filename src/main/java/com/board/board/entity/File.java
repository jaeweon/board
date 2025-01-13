package com.board.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String path;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String extension;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @Builder
    public File(String path, String name, String extension, Post post) {
        this.path = path;
        this.name = name;
        this.extension = extension;
        this.post = post;
    }
}
