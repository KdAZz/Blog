package com.kdazz.article.schedule;

import cn.hutool.json.JSONUtil;
import com.kdazz.article.conf.ServerConfigurer;
import com.kdazz.common.dto.LikeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.kdazz.common.constant.GlobalConstants.LIKE_WAIT_DElETE_SET;
import static com.kdazz.common.constant.GlobalConstants.LIKE_WAIT_SET;

@Slf4j
@Component
@AllArgsConstructor
public class PushTask {
    private final StringRedisTemplate redisTemplate;

    private final ServerConfigurer serverConfigurer;

    private final WaitProcessor waitProcessor;

    @Scheduled(cron = "0 6/20 * * * ?")
    public void waitPushLikeTask() {
        waitTask(LIKE_WAIT_SET, waitProcessor::pushWait);
    }

    @Scheduled(cron = "0 15/20 * * * ? ")
    public void deleteWaitLike() {
        waitTask(LIKE_WAIT_DElETE_SET, waitProcessor::deleteLike);
    }

    public void waitTask(String str, Consumer<LikeDto> consumer) {
        Long size = redisTemplate.opsForSet().size(str);
        //redis里面没有要消费的数据
        if (size == null || size == 0) {
            return;
        }
        int handlerMaxSize = serverConfigurer.getHandlerMaxSize();
        if (size > handlerMaxSize) {
            size = (long) handlerMaxSize;
        }

        List<LikeDto> likeDtos = new ArrayList<>(size.intValue());

        for (int i = 0; i < size; i++) {
            Object obj = redisTemplate.opsForSet().pop(str);
            if (obj != null) {
                LikeDto likeDto = JSONUtil.toBean(JSONUtil.parseObj(obj), LikeDto.class);
                likeDtos.add(likeDto);
            }
        }

        likeDtos.forEach(consumer);
    }
}
