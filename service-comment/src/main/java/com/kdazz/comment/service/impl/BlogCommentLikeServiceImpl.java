package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.BlogLikeMapper;
import com.kdazz.comment.pojo.entity.BlogLike;
import com.kdazz.comment.service.IBlogCommentLikeService;
import com.kdazz.common.dto.LikeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BlogCommentLikeServiceImpl extends ServiceImpl<BlogLikeMapper, BlogLike> implements IBlogCommentLikeService {

    @Override
    public int pushLike(LikeDto likeDto) {
        BlogLike blogLike = new BlogLike();
        blogLike.setBlogId(likeDto.getArticleId());
        blogLike.setUserId(likeDto.getUserId());
        return baseMapper.insert(blogLike);
    }

    @Override
    public void deleteLike(LikeDto likeDto) {
        baseMapper.delete(query().eq("blog_id",
                likeDto.getArticleId()).eq("user_id", likeDto.getUserId()));
    }
}
