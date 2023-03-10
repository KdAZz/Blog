package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.BlogCommentLikeMapper;
import com.kdazz.comment.pojo.entity.BlogCommentLike;
import com.kdazz.comment.service.IBlogCommentLikeService;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;
import com.kdazz.redis.util.LikeHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogCommentLikeServiceImpl extends ServiceImpl<BlogCommentLikeMapper, BlogCommentLike> implements IBlogCommentLikeService {

    private final LikeHandler handler;

    @Override
    public void pushLike(LikeDto likeDto) {
        baseMapper.insertLike(likeDto);
    }

    @Override
    public void deleteLike(LikeDto likeDto) {
        baseMapper.delete(query().eq("blog_id",
                likeDto.getArticleId()).eq("user_id", likeDto.getUserId()));
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

}
