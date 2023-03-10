package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.ArticleLikeMapper;
import com.kdazz.comment.pojo.entity.ArticleLike;
import com.kdazz.comment.service.IArticleCommentLikeService;
import com.kdazz.common.dto.LikeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticleCommentLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements IArticleCommentLikeService {

    @Override
    public int pushLike(LikeDto likeDto) {
        ArticleLike articleLike = new ArticleLike();
        articleLike.setArticleId(likeDto.getArticleId());
        articleLike.setUserId(likeDto.getUserId());
        return baseMapper.insert(articleLike);
    }

    @Override
    public void deleteLike(LikeDto likeDto) {
        baseMapper.delete(query().eq("article_id",
                likeDto.getArticleId()).eq("user_id", likeDto.getUserId()));
    }
}
