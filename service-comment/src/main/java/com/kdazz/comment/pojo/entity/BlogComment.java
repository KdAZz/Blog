package com.kdazz.comment.pojo.entity;

import lombok.Data;

@Data
public class BlogComment {
    Long id;

    Long userId;

    Long blogId;

    Long commentLike;

    Long commentDislike;

    String commentContent;

    Long parentCommentId;
}
