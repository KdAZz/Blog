package com.kdazz.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.comment.pojo.entity.BlogCommentLike;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;

public interface IBlogCommentLikeService extends IService<BlogCommentLike> {
    void pushLike(LikeDto likeDto);

    void deleteLike(LikeDto likeDto);

    R<?> addLike(LikeDto likeDto);

    R<?> disLike(LikeDto likeDto);

    R<?> removeLike(LikeDto likeDto);

    R<?> removeDislike(LikeDto likeDto);
}
