package com.kdazz.article.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleLikeCountVo {
    private Long articleId;

    private Long likeNum;
}
