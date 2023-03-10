package com.kdazz.comment.schedule;

import com.kdazz.comment.service.IArticleCommentLikeService;
import com.kdazz.comment.service.IBlogCommentLikeService;
import com.kdazz.common.dto.LikeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
@AllArgsConstructor
public class WaitProcessor {
    private final ThreadPoolExecutor handlerOrderExecutor;

    private final IArticleCommentLikeService articleCommentLikeService;

    private final IBlogCommentLikeService blogCommentLikeService;

    public void pushArticleCommentLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> articleCommentLikeService.pushLike(likeDto));
    }

    public void deleteArticleCommentLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> articleCommentLikeService.deleteLike(likeDto));
    }

    public void pushBlogCommentLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> blogCommentLikeService.pushLike(likeDto));
    }

    public void deleteBlogCommentLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> blogCommentLikeService.deleteLike(likeDto));
    }

    //TODO 创建失败队列
}
