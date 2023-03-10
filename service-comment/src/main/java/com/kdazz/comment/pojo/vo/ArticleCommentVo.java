package com.kdazz.comment.pojo.vo;

import com.kdazz.comment.pojo.entity.ArticleComment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleCommentVo extends ArticleComment {

    String userName;

    Long likeNum;

    Long dislikeNum;

    List<ArticleCommentVo> childArticleComments;
}
