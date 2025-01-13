package com.board.board.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    @Builder
    public Tag(String name) {
        this.name = name;
    }
}
