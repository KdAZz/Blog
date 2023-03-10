package com.kdazz.comment.pojo.entity;

import lombok.Data;

@Data
public class ArticleCommentLike {
    private Long articleId;

    private Long userId;
}
