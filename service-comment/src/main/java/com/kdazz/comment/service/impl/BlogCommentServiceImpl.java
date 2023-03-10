package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.BlogCommentMapper;
import com.kdazz.comment.pojo.entity.BlogComment;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import com.kdazz.comment.service.IBlogCommentService;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;
import com.kdazz.redis.util.LikeHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.kdazz.common.constant.GlobalConstants.COMMENT_BLOG_DISLIKE_SET;
import static com.kdazz.common.constant.GlobalConstants.COMMENT_BLOG_LIKE_SET;

@Service
@RequiredArgsConstructor
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

    private final LikeHandler handler;

    private final StringRedisTemplate redisTemplate;

    @Override
    public IPage<BlogCommentVo> getCommentByBlogId(Page<BlogCommentVo> page, Long blogId) {
        IPage<BlogCommentVo> vo = this.baseMapper.getCommentByBlogId(page, blogId, 0L);
        vo.getRecords().forEach(item -> {
            item.setCommentLike(redisTemplate.opsForSet().size(COMMENT_BLOG_LIKE_SET));
            item.setCommentDislike(redisTemplate.opsForSet().size(COMMENT_BLOG_DISLIKE_SET));
        });
        return vo;
    }

    @Override
    public R<?> addLike(LikeDto likeDto) {
        return handler.likeHandler(likeDto, false, true, true);
    }

    @Override
    public R<?> disLike(LikeDto likeDto) {
        return handler.likeHandler(likeDto, false, true, false);
    }

    @Override
    public R<?> removeLike(LikeDto likeDto) {
        return handler.likeHandler(likeDto, false, false, true);
    }

    @Override
    public R<?> removeDislike(LikeDto likeDto) {
        return handler.likeHandler(likeDto, false, false, false);
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
