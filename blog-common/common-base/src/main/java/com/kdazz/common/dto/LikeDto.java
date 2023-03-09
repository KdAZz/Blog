package com.kdazz.common.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LikeDto implements Serializable {
    private Long articleId;

    private Long userId;
}
