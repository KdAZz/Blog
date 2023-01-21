package com.kdazz.article.pojo.vo;

import com.kdazz.article.pojo.entity.ArticleContent;
import lombok.Data;

@Data
public class ArticleDetailVo extends ArticleContent {
    private String username;

    private Long concernNum;

    private Long articleNum;

    private Long beConcernNum;
}
