package com.kdazz.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.article.pojo.entity.ArticleContent;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.service.IArticleLikeService;
import com.kdazz.article.service.IArticleService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final IArticleService articleService;

    private final IArticleLikeService articleLikeService;

    /**
     * 分页文章
     *
     * @param page     页数
     * @param pageSize 数量
     */
    @GetMapping("/page")
    public R<?> getPage(@RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        return R.ok(articleService.getPageList(new Page<>(page, pageSize)));
    }

    /**
     * ID获取文章
     *
     * @param articleId 文章ID
     */
    @GetMapping("/{articleId}")
    public R<?> getArticleById(@PathVariable("articleId") Long articleId) {
        return R.ok(articleService.getArticleById(articleId));
    }

    /**
     * 添加文章
     */
    @PostMapping("/createArticle")
    public R<?> saveArticle(@RequestBody ArticleContent articleContent) {
        return R.ok(articleService.save(articleContent));
    }

//    /**
//     * 点赞
//     */
//    @PutMapping("/like")
//    public R<?> saveArticleLike(@RequestParam Map<String, Object> param) {
//        ArticleLike like = new ArticleLike();
//        Long flag = Long.parseLong((String) param.get("likeType"));
//        like.setLikeType(flag.equals(1L));
//        like.setArticleId(Long.parseLong((String) param.get("articleId")));
//        like.setUserId(Long.parseLong((String) param.get("userId")));
//        articleLikeService.likeStatusChange(like);
//        return R.ok();
//    }

    @PutMapping("/addLike")
    public R<?> addLike(@RequestBody ArticleLike articleLike) {
        articleLikeService.save(articleLike);
        return R.ok();
    }
}
