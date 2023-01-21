package com.kdazz.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.article.pojo.vo.ArticleVo;

public interface IPageService extends IService<ArticleVo> {
    IPage<ArticleVo> getPageList(Page<ArticleVo> page);
}
