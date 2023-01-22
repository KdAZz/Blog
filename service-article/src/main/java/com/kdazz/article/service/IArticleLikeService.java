package com.kdazz.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.article.pojo.entity.ArticleLike;

import java.util.Map;

public interface IArticleLikeService extends IService<ArticleLike> {
    void likeStatusChange(ArticleLike articleLike);
}
