package com.kdazz.article.schedule;

import com.kdazz.common.dto.LikeDto;
import com.kdazz.article.service.IArticleLikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
@AllArgsConstructor
public class WaitProcessor {
    private final ThreadPoolExecutor handlerOrderExecutor;

    private final IArticleLikeService likeService;
    public void pushWait(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> likeService.pushLike(likeDto));
    }

    public void deleteLike(LikeDto likeDto) {
        handlerOrderExecutor.execute(() -> likeService.deleteLike(likeDto));
    }

    //TODO 创建失败队列
}
