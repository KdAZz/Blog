package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.ArticleCommentMapper;
import com.kdazz.comment.pojo.entity.ArticleComment;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;
import com.kdazz.comment.service.IArticleCommentService;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;
import com.kdazz.redis.util.LikeHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.kdazz.common.constant.GlobalConstants.COMMENT_ARTICLE_DISLIKE_SET;
import static com.kdazz.common.constant.GlobalConstants.COMMENT_ARTICLE_LIKE_SET;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements IArticleCommentService {

    private final LikeHandler handler;

    private final StringRedisTemplate redisTemplate;

    @Override
    public IPage<ArticleCommentVo> getCommentByArticleId(Page<ArticleCommentVo> page, Long articleId) {
        IPage<ArticleCommentVo> vo = this.baseMapper.getCommentByArticleId(page, articleId, 0L);
        vo.getRecords().forEach(item -> {
            item.setLikeNum(redisTemplate.opsForSet().size(COMMENT_ARTICLE_LIKE_SET));
            item.setDislikeNum(redisTemplate.opsForSet().size(COMMENT_ARTICLE_DISLIKE_SET));
        });
        return vo;
    }

    @Override
    public R<?> addLike(LikeDto likeDto) {
        return handler.likeHandler(likeDto, true, true, true);
    }

    @Override
    public R<?> disLike(LikeDto likeDto) {
        return handler.likeHandler(likeDto, true, true, false);
    }

    @Override
    public R<?> removeLike(LikeDto likeDto) {
        return handler.likeHandler(likeDto, true, false, true);
    }

    @Override
    public R<?> removeDislike(LikeDto likeDto) {
        return handler.likeHandler(likeDto, true, false, false);
    }

    @Override
    public void pushLike(LikeDto likeDto) {
        baseMapper.insertLike(likeDto);
    }

    @Override
    public void deleteLike(LikeDto likeDto) {
        baseMapper.delete(query().eq("article_id",
                likeDto.getArticleId()).eq("user_id", likeDto.getUserId()));
    }
}
