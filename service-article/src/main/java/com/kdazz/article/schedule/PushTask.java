package com.kdazz.article.schedule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdazz.article.conf.ServerConfigurer;
import com.kdazz.article.pojo.dto.LikeDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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
    private final RedisTemplate<String, Object> redisTemplate;

    private final ServerConfigurer serverConfigurer;

    private final WaitProcessor waitProcessor;

    @Scheduled(cron = "0 */10 * * * ?")
    public void waitPushLikeTask() throws JsonProcessingException {
        waitTask(LIKE_WAIT_SET, waitProcessor::pushWait);
    }

    @Scheduled(cron = "0 5/10 * * * ? ")
    public void deleteWaitLike() throws JsonProcessingException {
        waitTask(LIKE_WAIT_DElETE_SET, waitProcessor::deleteLike);
    }

    public void waitTask(String str, Consumer<Object> consumer) throws JsonProcessingException {
        redisTemplate.setValueSerializer(new StringRedisSerializer());
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
                ObjectMapper mapper = new ObjectMapper();
                LikeDto likeDto = mapper.readValue(obj.toString(), LikeDto.class);
                likeDtos.add(likeDto);
            }
        }

        likeDtos.forEach(consumer);
    }
}
