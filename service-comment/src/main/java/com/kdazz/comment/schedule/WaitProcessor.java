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
    public void pushArticleCommentLike(Object likeDto) {
        handlerOrderExecutor.execute(() -> articleCommentService.pushLike((LikeDto) likeDto));
    }

    public void deleteArticleCommentLike(Object likeDto) {
        handlerOrderExecutor.execute(() -> articleCommentService.deleteLike((LikeDto) likeDto));
    }

    public void pushBlogCommentLike(Object likeDto) {
        handlerOrderExecutor.execute(() -> blogCommentService.pushLike((LikeDto) likeDto));
    }

    public void deleteBlogCommentLike(Object likeDto) {
        handlerOrderExecutor.execute(() -> blogCommentService.deleteLike((LikeDto) likeDto));
    }

    //TODO 创建失败队列
}
