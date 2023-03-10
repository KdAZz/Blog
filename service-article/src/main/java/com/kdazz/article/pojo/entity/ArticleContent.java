package com.kdazz.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleContent {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long authorId;

    private String title;

    private String content;

    private Date createTime;

    private Date modifyTime;

}
