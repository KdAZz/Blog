package com.kdazz.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Operator {
    private Long articleId;

    private Long userId;

    private String likeSet;

    private String dislikeSet;

    private String waitPushLike;

    private String waitDeleteLike;

    private String waitPushDisLike;

    private String waitDeleteDislike;

    private Boolean type;
}