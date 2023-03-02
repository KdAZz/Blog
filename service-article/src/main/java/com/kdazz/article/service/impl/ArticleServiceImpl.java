package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.admin.api.UserFeignClient;
import com.kdazz.article.mapper.ArticleMapper;
import com.kdazz.article.pojo.entity.ArticleCategory;
import com.kdazz.article.pojo.entity.ArticleContent;
import com.kdazz.article.pojo.vo.ArticleDetailVo;
import com.kdazz.article.pojo.vo.ArticleVo;
import com.kdazz.article.service.IArticleCategoryService;
import com.kdazz.article.service.IArticleLikeService;
import com.kdazz.article.service.IArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import static com.kdazz.common.constant.GlobalConstants.ARTICLE_CATEGORY;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleContent> implements IArticleService {

    private final RedisTemplate redisTemplate;
    private final IArticleCategoryService categoryService;
    private final UserFeignClient userClient;

    private final IArticleLikeService likeService;

    @Override
    public ArticleDetailVo getArticleById(Long articleId) {
        ArticleContent content = this.baseMapper.selectById(articleId);
        ArticleDetailVo vo = new ArticleDetailVo();
        vo.setContent(content);
        vo.setAuthorName(userClient.getAuthorNameById(content.getAuthorId()).getData());
        //TODO 连表查询，获取分类ID
        ArticleCategory articleCategory = (ArticleCategory) redisTemplate.opsForHash().get(ARTICLE_CATEGORY, articleId.toString());
        if (articleCategory == null) {
            articleCategory = (ArticleCategory) categoryService.getArticleCategoryById(articleId).getData();
        }
        vo.setCategory(articleCategory);
        return vo;
    }

    @Override
    public IPage<ArticleVo> getPageList(Page<ArticleVo> page) {
        IPage<ArticleVo> voPage = this.baseMapper.getPage(page);
        voPage.getRecords().forEach(item -> item.setLikeCount(likeService.getLikeCount(item.getId())));
        return voPage;
    }

    @Override
    public IPage<ArticleVo> getArticleByCategory(Page<ArticleVo> page,Long categoryId) {
        IPage<ArticleVo> voPage = baseMapper.getArticleByCategory(page, categoryId);
        voPage.getRecords().forEach(item -> item.setLikeCount(likeService.getLikeCount(item.getId())));
        return voPage;
    }
}
