package com.kdazz.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.article.pojo.entity.ArticleContent;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.pojo.vo.ArticleDetailVo;
import com.kdazz.article.pojo.vo.ArticleVo;

public interface IArticleService extends IService<ArticleContent> {
    ArticleDetailVo getArticleById(Long userId);

    IPage<ArticleVo> getPageList(Page<ArticleVo> page);
}
