package com.kdazz.comment.utils;

import com.kdazz.common.dto.LikeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static com.kdazz.common.constant.GlobalConstants.*;

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

    public void useScript(LikeDto likeDto, Boolean type) {
        log.info(redisTemplate.execute(
                ADD_ARTICLE_LIKE_SCRIPT,
                Collections.emptyList(),
                likeDto.getArticleId().toString(), likeDto.getUserId().toString(), COMMENT_ARTICLE_LIKE_SET,
                COMMENT_ARTICLE_DISLIKE_SET, COMMENT_ARTICLE_LIKE_WAIT, COMMENT_ARTICLE_LIKE_WAIT_DELETE,
                COMMENT_ARTICLE_DISLIKE_WAIT, COMMENT_ARTICLE_DISLIKE_WAIT_DELETE, type.toString()
        ).equals(1L) ? "like success" : "dislike success");
    }

    public void useBlogScript(LikeDto likeDto, Boolean type) {
        log.info(redisTemplate.execute(
                ADD_ARTICLE_LIKE_SCRIPT,
                Collections.emptyList(),
                likeDto.getArticleId().toString(), likeDto.getUserId().toString(), COMMENT_BLOG_LIKE_SET,
                COMMENT_BLOG_DISLIKE_SET, COMMENT_BLOG_LIKE_WAIT, COMMENT_BLOG_LIKE_WAIT_DELETE,
                COMMENT_BLOG_DISLIKE_WAIT, COMMENT_BLOG_DISLIKE_WAIT_DELETE, type.toString()
        ).equals(1L) ? "like success" : "dislike success");
    }
}
