package com.kdazz.redis.util;

import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;
import com.kdazz.redis.entity.Operator;
import lombok.RequiredArgsConstructor;

import static com.kdazz.common.constant.GlobalConstants.*;
import static com.kdazz.common.constant.GlobalConstants.COMMENT_ARTICLE_DISLIKE_WAIT_DELETE;

@RequiredArgsConstructor
public class LikeHandler {
    private final LuaUtil luaUtil;

    private final LuaRemUtil remUtil;

    /**
     * @param likeDto likeDto
     * @param likeFrom true: article false: blog
     * @param addOrRem true: add false: remove
     * @param likeType true: like false:dislike
     */
    public R<?> likeHandler(LikeDto likeDto, Boolean likeFrom, Boolean addOrRem, Boolean likeType) {
        Operator operator;
        if (likeFrom) {
            operator = new Operator(likeDto.getArticleId(), likeDto.getUserId(), COMMENT_ARTICLE_LIKE_SET,
                    COMMENT_ARTICLE_DISLIKE_SET, COMMENT_ARTICLE_LIKE_WAIT, COMMENT_ARTICLE_LIKE_WAIT_DELETE,
                    COMMENT_ARTICLE_DISLIKE_WAIT, COMMENT_ARTICLE_DISLIKE_WAIT_DELETE, likeType);
        } else {
            operator = new Operator(likeDto.getArticleId(), likeDto.getUserId(), COMMENT_BLOG_LIKE_SET,
                    COMMENT_BLOG_DISLIKE_SET, COMMENT_BLOG_LIKE_WAIT, COMMENT_BLOG_LIKE_WAIT_DELETE,
                    COMMENT_BLOG_DISLIKE_WAIT, COMMENT_BLOG_DISLIKE_WAIT_DELETE, likeType);
        }
        if (addOrRem) {
            return luaUtil.addLike(operator);
        } else {
            return remUtil.removeLike(operator);
        }
    }
}
