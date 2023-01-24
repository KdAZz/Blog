package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.ArticleLikeMapper;
import com.kdazz.article.pojo.dto.ArticleLikeDto;
import com.kdazz.article.pojo.dto.LikeDto;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.pojo.vo.ArticleLikeCountVo;
import com.kdazz.article.service.IArticleLikeService;
import com.kdazz.common.constant.GlobalConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements IArticleLikeService {

    private final RedisTemplate redisTemplate;

    // 获取当前文章的喜欢数量
    public ArticleLikeCountVo getLikeCount(Long articleId) {
        ArticleLikeCountVo likeVo = (ArticleLikeCountVo) redisTemplate.opsForHash().get(GlobalConstants.ARTICLE_LIKE_COUNT, articleId.toString());
        System.out.println(likeVo);
        if (likeVo != null) {
            return likeVo;
        }
        ArticleLikeCountVo vo =this.getLikeToJson(articleId);
        redisTemplate.opsForHash().put(GlobalConstants.ARTICLE_LIKE_COUNT, articleId.toString(), vo);
        return vo;
    }

    @Override
    public Boolean addLike(Boolean bool, LikeDto likeDto) {
        String articleId = likeDto.getArticleId().toString();
        ArticleLikeCountVo likeCountVo = (ArticleLikeCountVo) redisTemplate.opsForHash().get(GlobalConstants.ARTICLE_LIKE_COUNT, articleId);
        Boolean isLike = redisTemplate.opsForSet().isMember(GlobalConstants.ARTICLE_LIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
        Boolean dislike = redisTemplate.opsForSet().isMember(GlobalConstants.ARTICLE_DISLIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
        assert likeCountVo != null;
        if (bool) {
            if (!isLike) {
                redisTemplate.opsForSet().add(GlobalConstants.ARTICLE_LIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
                likeCountVo.setLikeNum(likeCountVo.getLikeNum() + 1L);
                redisTemplate.opsForHash().put(GlobalConstants.ARTICLE_LIKE_COUNT, likeCountVo.getArticleId().toString(), likeCountVo);
            }
            if (dislike) {
                redisTemplate.opsForSet().remove(GlobalConstants.ARTICLE_DISLIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
                likeCountVo.setDislikeNum(likeCountVo.getDislikeNum() - 1L);
                redisTemplate.opsForHash().put(GlobalConstants.ARTICLE_LIKE_COUNT, likeCountVo.getArticleId().toString(), likeCountVo);
            }
            return true;
        }
        if (!dislike) {
            redisTemplate.opsForSet().add(GlobalConstants.ARTICLE_DISLIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
            likeCountVo.setDislikeNum(likeCountVo.getDislikeNum() + 1L);
            redisTemplate.opsForHash().put(GlobalConstants.ARTICLE_LIKE_COUNT, likeCountVo.getArticleId().toString(), likeCountVo);
        }
        if (isLike) {
            redisTemplate.opsForSet().remove(GlobalConstants.ARTICLE_LIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
            likeCountVo.setLikeNum(likeCountVo.getLikeNum() - 1);
            redisTemplate.opsForHash().put(GlobalConstants.ARTICLE_LIKE_COUNT, likeCountVo.getArticleId().toString(), likeCountVo);
        }
        return true;
    }

    @Override
    public Boolean removeLike(Boolean bool, LikeDto likeDto) {
        if (bool) {
            Boolean isLike = redisTemplate.opsForSet().isMember(GlobalConstants.ARTICLE_LIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
            if (isLike) {
                redisTemplate.opsForSet().remove(GlobalConstants.ARTICLE_LIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
                ArticleLikeCountVo likeVo = (ArticleLikeCountVo) redisTemplate.opsForHash().get(GlobalConstants.ARTICLE_LIKE_COUNT, likeDto.getArticleId().toString());
                likeVo.setLikeNum(likeVo.getLikeNum() - 1);
                redisTemplate.opsForHash().put(GlobalConstants.ARTICLE_LIKE_COUNT, likeVo.getArticleId().toString(), likeVo);
            }
            return true;
        }
        Boolean disLike = redisTemplate.opsForSet().isMember(GlobalConstants.ARTICLE_DISLIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
        if (disLike) {
            redisTemplate.opsForSet().remove(GlobalConstants.ARTICLE_DISLIKE_USER + likeDto.getArticleId(), likeDto.getUserId());
            ArticleLikeCountVo likeVo = (ArticleLikeCountVo) redisTemplate.opsForHash().get(GlobalConstants.ARTICLE_LIKE_COUNT, likeDto.getArticleId().toString());
            System.out.println(likeVo);
            likeVo.setDislikeNum(likeVo.getDislikeNum() - 1);
            redisTemplate.opsForHash().put(GlobalConstants.ARTICLE_LIKE_COUNT, likeVo.getArticleId().toString(), likeVo);
        }
        return true;
    }

    public ArticleLikeCountVo getLikeToJson(Long articleId) {
        ArticleLikeCountVo likeCountVo = new ArticleLikeCountVo(articleId, 0L, 0L);
        List<ArticleLikeDto> dtoList = this.baseMapper.getLikeCount(articleId);
        dtoList.forEach(item -> {
            if (item.getLikeType()) {
                likeCountVo.setLikeNum(item.getCount());
            } else {
                likeCountVo.setDislikeNum(item.getCount());
            }
        });
        return likeCountVo;
    }

}
