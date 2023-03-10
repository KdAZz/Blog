package com.kdazz.comment.pojo.vo;

import com.kdazz.comment.pojo.entity.BlogComment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogCommentVo extends BlogComment {

    String userName;

    Long commentLike;

    Long commentDislike;

    List<ArticleCommentVo> childBlogComments;
}
