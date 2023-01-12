package com.kdazz.article.service.impl;

import com.kdazz.article.mapper.ArticleMapper;
import com.kdazz.article.service.IArticleService;
import com.kdazz.pojo.entity.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleImplement implements IArticleService {

    private final ArticleMapper articleMapper;

    @Override
    public List<Article> getArticle() {
        return articleMapper.selectList(null);
    }
}
