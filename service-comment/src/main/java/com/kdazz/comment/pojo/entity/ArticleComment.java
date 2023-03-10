package com.kdazz.comment.pojo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ArticleComment {
    Long id;

    Long userId;

    Long articleId;

    String commentContent;

    Long parentCommentId;
}
