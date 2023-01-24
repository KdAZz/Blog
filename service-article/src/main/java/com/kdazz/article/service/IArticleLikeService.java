package com.kdazz.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.article.pojo.dto.LikeDto;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.pojo.vo.ArticleLikeCountVo;

public interface IArticleLikeService extends IService<ArticleLike> {

    ArticleLikeCountVo getLikeCount(Long articleId);

    Boolean addLike(Boolean bool, LikeDto likeDto);

    Boolean removeLike(Boolean bool, LikeDto likeDto);
}
