package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.ArticleCategoryMapper;
import com.kdazz.article.pojo.entity.ArticleCategory;
import com.kdazz.article.service.IArticleCategoryService;
import com.kdazz.common.result.R;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kdazz.common.constant.GlobalConstants.ARTICLE_CATEGORY;

@AllArgsConstructor
@Service
public class ArticleCategoryImpl extends ServiceImpl<ArticleCategoryMapper, ArticleCategory> implements IArticleCategoryService {

    public final RedisTemplate redisTemplate;
    @Override
    public R getArticleCategory() {
        List<ArticleCategory> list = redisTemplate.opsForHash().values(ARTICLE_CATEGORY);
        if (list.size() == 0) {
            list = this.baseMapper.selectList(null);
            list.forEach(item -> {
                redisTemplate.opsForHash().put(ARTICLE_CATEGORY, item.getId().toString(), item);
            });
        }
        return R.ok(list);
    }

    @Override
    public R<?> getArticleCategoryById(Long articleId){
        ArticleCategory category = baseMapper.selectById(articleId);
        redisTemplate.opsForHash().put(ARTICLE_CATEGORY, category.getId().toString(), category);
        return R.ok(category);
    }
}
