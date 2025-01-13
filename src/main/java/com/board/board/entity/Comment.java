package com.board.board.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @Column(nullable = false, length = 300)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "subCommentId")
    private Comment subComment;

    @Builder
    public Comment(Post post, String contents, Comment subComment) {
        this.post = post;
        this.contents = contents;
        this.subComment = subComment;
    }
}
