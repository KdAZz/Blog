package com.kdazz.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BlogList {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String content;

    private Long commentCount;

    private Long forwardCount;

    private Long likeCount;
}
