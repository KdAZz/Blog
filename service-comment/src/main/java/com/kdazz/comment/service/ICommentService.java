package com.kdazz.comment.service;

import com.kdazz.comment.pojo.entity.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getCommentByArticleId(Long articleId);
}
