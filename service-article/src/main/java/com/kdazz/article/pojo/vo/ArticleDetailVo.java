package com.kdazz.article.pojo.vo;

import com.kdazz.article.pojo.entity.ArticleCategory;
import com.kdazz.article.pojo.entity.ArticleContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class ArticleDetailVo {
    private String authorName;

    private ArticleCategory category;

    private ArticleContent content;
}
