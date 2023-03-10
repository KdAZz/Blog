package com.kdazz.comment.schedule;

import com.kdazz.comment.service.IArticleCommentService;
import com.kdazz.comment.service.IBlogCommentService;
import com.kdazz.common.dto.LikeDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
@AllArgsConstructor
public class WaitProcessor {
    private final ThreadPoolExecutor handlerOrderExecutor;

    private final IArticleCommentService articleCommentService;

    private final IBlogCommentService blogCommentService;
    public void pushArticleCommentLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> articleCommentService.pushLike(likeDto));
    }

    public void deleteArticleCommentLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> articleCommentService.deleteLike(likeDto));
    }

    public void pushBlogCommentLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> blogCommentService.pushLike(likeDto));
    }

    public void deleteBlogCommentLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> blogCommentService.deleteLike(likeDto));
    }

    //TODO 创建失败队列
}
