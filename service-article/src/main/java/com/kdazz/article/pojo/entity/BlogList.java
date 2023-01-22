package com.kdazz.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class BlogList {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String content;

    private Long commentCount;

    private Long forwardCount;

    private Long likeNum;

    private Long dislikeNum;

    private Date createTime;
}
