package com.kdazz.comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;

public interface IArticleCommentService {
    IPage<ArticleCommentVo> getCommentByArticleId(Page<ArticleCommentVo> page, Long articleId);
}
