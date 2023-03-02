package com.kdazz.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.article.pojo.dto.LikeDto;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.common.result.R;

public interface IArticleLikeService extends IService<ArticleLike> {

    Long getLikeCount(Long articleId);

    R addLike(LikeDto likeDto);

    void pushLike(LikeDto likeDto);

    R removeLike(LikeDto likeDto);

    R deleteLike(LikeDto likeDto);
}
