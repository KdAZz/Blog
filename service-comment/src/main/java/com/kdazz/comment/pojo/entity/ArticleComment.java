package com.kdazz.comment.pojo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ArticleComment {
    Long id;

    Long userId;

    Long articleId;

    Long commentLike;

    Long commentDislike;

    String commentContent;

    Long parentCommentId;
}
