package com.kdazz.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.comment.pojo.entity.BlogLike;
import com.kdazz.common.dto.LikeDto;

public interface IBlogCommentLikeService extends IService<BlogLike> {
    int pushLike(LikeDto likeDto);

    void deleteLike(LikeDto likeDto);
}
