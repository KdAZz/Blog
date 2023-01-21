package com.kdazz.comment.pojo.vo;

import com.kdazz.comment.pojo.entity.BlogComment;
import lombok.Data;

import java.util.List;

@Data
public class BlogCommentVo extends BlogComment {

    String userName;

    List<ArticleCommentVo> childBlogComments;
}
