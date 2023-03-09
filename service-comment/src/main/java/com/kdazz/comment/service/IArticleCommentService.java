package com.kdazz.comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;
import com.kdazz.common.result.R;

public interface IArticleCommentService {
    IPage<ArticleCommentVo> getCommentByArticleId(Page<ArticleCommentVo> page, Long articleId);

    R<?> addLike(LikeDto likeDto);

    R<?> disLike(LikeDto likeDto);

    R<?> removeLike(LikeDto likeDto);

    R<?> removeDislike(LikeDto likeDto);

    void pushLike(LikeDto likeDto);

    void deleteLike(LikeDto likeDto);
}
