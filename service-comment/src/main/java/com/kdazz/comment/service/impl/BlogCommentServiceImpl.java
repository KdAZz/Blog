package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.BlogCommentMapper;
import com.kdazz.comment.pojo.entity.BlogComment;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import com.kdazz.comment.service.IBlogCommentService;
import com.kdazz.comment.utils.LuaRemUtil;
import com.kdazz.comment.utils.LuaUtil;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import static com.kdazz.common.constant.GlobalConstants.*;

@Service
@RequiredArgsConstructor
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

    private final LuaUtil luaUtil;

    private final LuaRemUtil remUtil;

    private final StringRedisTemplate redisTemplate;

    @Override
    public IPage<BlogCommentVo> getCommentByBlogId(Page<BlogCommentVo> page, Long blogId) {
        return this.baseMapper.getCommentByBlogId(page, blogId, 0L);
    }

    @Override
    public R<?> addLike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(COMMENT_BLOG_LIKE_SET + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (Boolean.TRUE.equals(member)) {
            return R.failed("已点赞");
        }
        luaUtil.useBlogScript(likeDto, false);
        return R.ok("点赞成功");
    }

    @Override
    public R<?> disLike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(COMMENT_BLOG_DISLIKE_SET + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (Boolean.TRUE.equals(member)) {
            return R.failed("已点踩");
        }
        luaUtil.useBlogScript(likeDto, false);
        return R.ok("点踩成功");
    }

    @Override
    public R<?> removeLike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(COMMENT_BLOG_LIKE_SET + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (!Boolean.TRUE.equals(member)) {
            return R.failed("点赞已取消");
        }
        remUtil.useBlogScript(likeDto, true);
        return R.ok("成功取消点赞");
    }

    @Override
    public R<?> removeDislike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(COMMENT_BLOG_DISLIKE_SET + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (!Boolean.TRUE.equals(member)) {
            return R.failed("点踩已取消");
        }
        remUtil.useBlogScript(likeDto, false);
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
