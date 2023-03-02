package com.kdazz.article.pojo.vo;

import com.kdazz.article.pojo.entity.ArticleContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleVo extends ArticleContent {
    private String userName;

    private Long likeCount;
}
