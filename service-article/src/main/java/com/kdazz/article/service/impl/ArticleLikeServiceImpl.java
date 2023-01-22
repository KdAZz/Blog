package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.ArticleLikeMapper;
import com.kdazz.article.mapper.ArticleMapper;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.service.IArticleLikeService;
import com.kdazz.article.service.IArticleService;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements IArticleLikeService {

    private final ArticleMapper articleMapper;

    @Transactional
    @Override
    public void likeStatusChange(ArticleLike articleLike) {
        QueryWrapper<ArticleLike> qwr = new QueryWrapper<>();
        qwr.eq("user_id", articleLike.getUserId());
        qwr.eq("article_id", articleLike.getArticleId());
        ArticleLike articleLike1 = this.baseMapper.selectOne(qwr);
        // 判断当前用户在article_like表中是否有记录，有则进行修改
        if (articleLike1 != null) {
            // 当状态不同时进行修改
            if (!articleLike.getLikeType().equals(articleLike1.getLikeType())) {
                LambdaUpdateWrapper<ArticleLike> luw = new LambdaUpdateWrapper<>();
                luw.eq(ArticleLike::getUserId, articleLike.getUserId())
                        .eq(ArticleLike::getArticleId, articleLike.getArticleId())
                        .set(ArticleLike::getLikeType, articleLike.getLikeType());
                this.baseMapper.update(null, luw);
                articleMapper.changeLike(articleLike);
            }
        } else {
            this.baseMapper.insert(articleLike);
            articleMapper.changeLikeOne(true, articleLike);
        }
    }
}
