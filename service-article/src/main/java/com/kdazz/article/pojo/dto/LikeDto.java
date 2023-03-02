package com.kdazz.article.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LikeDto implements Serializable {
    private Long articleId;

    private Long userId;
}
