package com.kdazz.comment.pojo.vo;

import com.kdazz.comment.pojo.entity.ArticleComment;
import lombok.Data;

import java.util.List;

@Data
public class ArticleCommentVo extends ArticleComment {

    String userName;

    List<ArticleCommentVo> childArticleComments;
}
