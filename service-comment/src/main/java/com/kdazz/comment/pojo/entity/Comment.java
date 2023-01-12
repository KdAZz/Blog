package com.kdazz.comment.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Comment {
    Long id;

    Long userId;

    Long articleId;

    Long commentLike;

    Long commentDislike;

    String commentContent;

    Long parentCommentId;

    @TableField(exist = false)
    String userName;

    @TableField(exist = false)
    List<Comment> childComments;
}
