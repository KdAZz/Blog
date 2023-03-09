package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.ArticleCommentMapper;
import com.kdazz.comment.pojo.entity.ArticleComment;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;
import com.kdazz.comment.service.IArticleCommentService;
import com.kdazz.comment.utils.LuaRemUtil;
import com.kdazz.comment.utils.LuaUtil;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.kdazz.common.constant.GlobalConstants.COMMENT_ARTICLE_DISLIKE_SET;
import static com.kdazz.common.constant.GlobalConstants.COMMENT_ARTICLE_LIKE_SET;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements IArticleCommentService {
    private final StringRedisTemplate redisTemplate;

    private final LuaUtil luaUtil;

    private final LuaRemUtil remUtil;

    @Override
    public IPage<ArticleCommentVo> getCommentByArticleId(Page<ArticleCommentVo> page, Long articleId) {
        return this.baseMapper.getCommentByArticleId(page,articleId, 0L);
    }

    @Override
    public R<?> addLike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(COMMENT_ARTICLE_LIKE_SET + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (Boolean.TRUE.equals(member)) {
            return R.failed("已点赞");
        }

//        redisTemplate.opsForSet().remove(COMMENT_ARTICLE_LIKE_WAIT_DELETE, str);
//        // 删除不喜欢集合
//        redisTemplate.opsForSet().remove(COMMENT_ARTICLE_DISLIKE_SET, str);
//        // 删除等待删除队列
//        redisTemplate.opsForSet().remove()
//        redisTemplate.opsForSet().add(LIKE_SET_USER + likeDto.getArticleId(), likeDto.getUserId().toString());
//        //存入等待队列
//        redisTemplate.opsForSet().add(LIKE_WAIT_SET, str);

//        String str = JSONUtil.toJsonStr(likeDto);
        // 1.执行lua脚本
        luaUtil.useScript(likeDto, true);
        return R.ok("点赞成功");
    }

    @Override
    public R<?> disLike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(COMMENT_ARTICLE_DISLIKE_SET + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (Boolean.TRUE.equals(member)) {
            return R.failed("已点踩");
        }
        luaUtil.useScript(likeDto, false);
        return R.ok("点踩成功");
    }

    @Override
    public R<?> removeLike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(COMMENT_ARTICLE_LIKE_SET + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (!Boolean.TRUE.equals(member)) {
            return R.failed("点赞已取消");
        }
        remUtil.useScript(likeDto, true);
        return R.ok("成功取消点赞");
    }

    @Override
    public R<?> removeDislike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(COMMENT_ARTICLE_DISLIKE_SET + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (!Boolean.TRUE.equals(member)) {
            return R.failed("点踩已取消");
        }
        remUtil.useScript(likeDto, false);
        return R.ok("成功取消点踩");
    }

    @Override
    public void pushLike(LikeDto likeDto) {
        baseMapper.insertLike(likeDto);
    }

    @Override
    public void deleteLike(LikeDto likeDto) {
        baseMapper.delete(query().eq("article_id",
                likeDto.getArticleId()).eq("user_id", likeDto.getUserId()));
    }
}
