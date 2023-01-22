package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.ArticleLikeMapper;
import com.kdazz.article.mapper.ArticleMapper;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.service.IArticleLikeService;
import com.kdazz.article.service.IArticleService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements IArticleLikeService {

    private final ArticleMapper articleMapper;

    @Override
    public Boolean likeStatusChange(ArticleLike articleLike) {
        QueryWrapper qwr = new QueryWrapper<ArticleLike>();
        qwr.eq("user_id", articleLike.getUserId());
        qwr.eq("article_id", articleLike.getArticleId());
        ArticleLike articleLike1 = this.baseMapper.selectOne(qwr);
        //TODO 修改为动态SQL
        if (articleLike1 != null) {
            if (!articleLike1.getLikeType().equals(articleLike.getLikeType())) {
                articleMapper.addLike(articleLike.getUserId());
                articleMapper.reduceDislike(articleLike.getArticleId());
            }
            return true;
        }else {
        }
        return true;
    }
}
