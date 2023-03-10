package com.kdazz.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.comment.pojo.entity.ArticleLike;
import com.kdazz.common.dto.LikeDto;

public interface IArticleCommentLikeService extends IService<ArticleLike> {
    int pushLike(LikeDto likeDto);

    void deleteLike(LikeDto likeDto);
}
