package com.kdazz.article.pojo.entity;

import lombok.Data;

@Data
public class ArticleLike {
    private Long userId;

    private Boolean likeType;

    private Long articleId;
    
}
