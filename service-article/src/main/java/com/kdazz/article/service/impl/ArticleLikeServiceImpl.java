package com.kdazz.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.ArticleLikeMapper;
import com.kdazz.article.pojo.dto.LikeDto;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.service.IArticleLikeService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kdazz.common.constant.GlobalConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleLikeServiceImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements IArticleLikeService {

    private final StringRedisTemplate redisTemplate;


    // 获取当前文章的喜欢数量
    public Long getLikeCount(Long articleId) {
        Boolean flag = redisTemplate.hasKey(LIKE_SET_USER + articleId);
        if (Boolean.FALSE.equals(flag)) {
            List<String> list = baseMapper.getListUserList(articleId);
            list.forEach(item -> redisTemplate.opsForSet().add(LIKE_SET_USER + articleId, item));
            return (long) list.size();
        }
        return redisTemplate.opsForSet().size(LIKE_SET_USER + articleId);
    }

    //点赞缓存队列
    @Override
    public R addLike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(LIKE_SET_USER + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (Boolean.TRUE.equals(member)) {
            return R.failed("已点赞");
        }
        String str = JSON.toJSONString(likeDto);
        //检测删除队列
        if (Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(LIKE_WAIT_DElETE_SET, str))) {
            redisTemplate.opsForSet().remove(LIKE_WAIT_DElETE_SET, str);
        }
        redisTemplate.opsForSet().add(LIKE_SET_USER + likeDto.getArticleId(), likeDto.getUserId().toString());
        //存入等待队列
        redisTemplate.opsForSet().add(LIKE_WAIT_SET, str);
        return R.ok("点赞成功");
    }

    //取消点赞缓存队列执行
    @Override
    public R removeLike(LikeDto likeDto) {
        Boolean member = redisTemplate.opsForSet().isMember(LIKE_SET_USER + likeDto.getArticleId(), likeDto.getUserId().toString());
        if (Boolean.TRUE.equals(member)) {
            redisTemplate.opsForSet().remove(LIKE_SET_USER + likeDto.getArticleId(), likeDto.getUserId().toString());
        }
        //删除等待推送的数据
        String str = JSON.toJSONString(likeDto);
        if (Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(LIKE_WAIT_SET, str))) {
            redisTemplate.opsForSet().remove(LIKE_WAIT_SET, str);
        }
        redisTemplate.opsForSet().add(LIKE_WAIT_DElETE_SET, str);
        return R.ok("已取消");
    }

    //点赞落库
    @Override
    public void pushLike(LikeDto likeDto) {
        log.info(likeDto.toString());
        baseMapper.insertLike(likeDto);
    }

    //删除
    @Override
    public R deleteLike(LikeDto likeDto) {
        baseMapper.delete(query().eq("article_id",
                likeDto.getArticleId()).eq("user_id", likeDto.getUserId()));
        return R.ok();
    }
}
