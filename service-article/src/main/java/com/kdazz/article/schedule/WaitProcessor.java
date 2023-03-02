package com.kdazz.article.schedule;

import com.kdazz.article.pojo.dto.LikeDto;
import com.kdazz.article.service.IArticleLikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
@AllArgsConstructor
public class WaitProcessor {
    private final ThreadPoolExecutor handlerOrderExecutor;

    private final IArticleLikeService likeService;
    public void pushWait(Object likeDto) {
        handlerOrderExecutor.execute(() -> likeService.pushLike((LikeDto) likeDto));
    }

    public void deleteLike(Object likeDto) {
        handlerOrderExecutor.execute(() -> likeService.deleteLike((LikeDto) likeDto));
    }
}
