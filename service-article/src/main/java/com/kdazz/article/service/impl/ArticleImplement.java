package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.ArticleMapper;
import com.kdazz.article.pojo.entity.ArticleContent;
import com.kdazz.article.service.IArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleImplement extends ServiceImpl<ArticleMapper, ArticleContent> implements IArticleService {

}
