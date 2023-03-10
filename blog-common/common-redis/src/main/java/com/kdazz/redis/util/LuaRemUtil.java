package com.kdazz.redis.util;

import com.kdazz.common.result.R;
import com.kdazz.redis.entity.Operator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class LuaRemUtil {
    private static final DefaultRedisScript<Long> ADD_ARTICLE_LIKE_SCRIPT;

    private final StringRedisTemplate redisTemplate;

    static {
        ADD_ARTICLE_LIKE_SCRIPT = new DefaultRedisScript<>();
        ADD_ARTICLE_LIKE_SCRIPT.setLocation(new ClassPathResource("script/removeScript.lua"));
        ADD_ARTICLE_LIKE_SCRIPT.setResultType(Long.class);
    }

//    private void useScript(LikeDto likeDto, Boolean type, String likeSet, String dislikeSet, String waitPushLike,
//                          String waitDeleteLike, String waitPushDisLike, String waitDeleteDislike) {
//        log.info(redisTemplate.execute(
//                ADD_ARTICLE_LIKE_SCRIPT,
//                Collections.emptyList(),
//                likeDto.getArticleId().toString(), likeDto.getUserId().toString(), likeSet,
//                dislikeSet, waitPushLike, waitDeleteLike, waitPushDisLike, waitDeleteDislike, type.toString()
//        ).equals(1L) ? "like success" : "dislike success");
//    }

    public R<?> removeLike(Operator operator) {
        String set = operator.getType() ? operator.getLikeSet() : operator.getDislikeSet();
        Boolean member = redisTemplate.opsForSet().isMember(set + operator.getArticleId(), operator.getUserId().toString());
        if (!Boolean.TRUE.equals(member)) {
            return R.failed(operator.getType() ? "点赞已取消" : "点踩已取消");
        }
        log.info(Objects.equals(
                redisTemplate.execute(
                    ADD_ARTICLE_LIKE_SCRIPT,
                    Collections.emptyList(),
                    operator.getArticleId().toString(), operator.getUserId().toString(), operator.getLikeSet(),
                    operator.getDislikeSet(), operator.getWaitPushLike(), operator.getWaitDeleteLike(),
                    operator.getWaitPushDisLike(), operator.getWaitDeleteDislike(), operator.getType().toString()
                ), 1L) ? "like success" : "dislike success");
        return R.ok(operator.getType() ? "成功取消点赞" : "成功取消点踩");
    }
}
