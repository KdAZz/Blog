package com.kdazz.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ArticleCategory {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String categoryName;

    private Boolean isShow;

    private String categoryDescription;
}
