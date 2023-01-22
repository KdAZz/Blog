package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.ArticleMapper;
import com.kdazz.article.pojo.entity.ArticleContent;
import com.kdazz.article.pojo.vo.ArticleDetailVo;
import com.kdazz.article.pojo.vo.ArticleVo;
import com.kdazz.article.service.IArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleImplement extends ServiceImpl<ArticleMapper, ArticleContent> implements IArticleService {

    @Override
    public ArticleDetailVo getArticleById(Long articleId) {
        return this.baseMapper.getArticleById(articleId);
    }

    @Override
    public IPage<ArticleVo> getPageList(Page<ArticleVo> page) {
        return this.baseMapper.getPage(page);
    }

    @Override
    public Boolean addLike(Long id) {
        return baseMapper.addLike(id);
    }
}
