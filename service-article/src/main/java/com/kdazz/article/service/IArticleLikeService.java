package com.kdazz.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.article.pojo.entity.ArticleLike;

public interface IArticleLikeService extends IService<ArticleLike> {
    void likeStatusChange(ArticleLike articleLike);

    void likeBlog(ArticleLike articleLike);

    Boolean refreshLike();

    Integer getLikeCount(Long id);
}
