package com.kdazz.article.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ArticleLike implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;

    private Boolean likeType;

    private Long articleId;
    
}
