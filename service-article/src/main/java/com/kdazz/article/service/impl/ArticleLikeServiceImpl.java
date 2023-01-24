package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.ArticleLikeMapper;
import com.kdazz.article.mapper.ArticleMapper;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.service.IArticleLikeService;
import com.kdazz.common.constant.GlobalConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements IArticleLikeService {

    private final ArticleMapper articleMapper;

    private final RedisTemplate redisTemplate;

    public Boolean refreshLike(){
        redisTemplate.delete(Arrays.asList(GlobalConstants.ARTICLE_LIKE_COUNT));
        //TODO 用redis存储点赞数据
//        if (this.getLikeCount()) {
//
//        }
        return null;
    }

    @Override
    public void likeBlog(ArticleLike articleLike) {

    }

    public Integer getLikeCount(Long articleId) {
        LambdaQueryWrapper<ArticleLike> query = new LambdaQueryWrapper<>();
        query.eq(ArticleLike::getArticleId, articleId)
                .eq(ArticleLike::getLikeType, 1L);
        return this.baseMapper.selectCount(query);
    }

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
        // 不存在直接添加
        } else {
            this.baseMapper.insert(articleLike);
            articleMapper.changeLikeOne(true, articleLike);
        }
    }

}
