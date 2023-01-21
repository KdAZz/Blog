package com.kdazz.comment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import com.kdazz.comment.service.IArticleCommentService;
import com.kdazz.comment.service.IBlogCommentService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final IArticleCommentService articleCommentService;

    private final IBlogCommentService blogCommentService;

    @GetMapping("/articleId/{articleId}")
    public R getCommentByArticleId(@PathVariable Long articleId){
        IPage<ArticleCommentVo> page = articleCommentService.getCommentByArticleId(new Page<>(1, 10),articleId);
        return R.ok(page);
    }

    @GetMapping("/blogId/{blogId}")
    public R getCommentByBlogId(@PathVariable Long blogId){
        IPage<BlogCommentVo> page = blogCommentService.getCommentByBlogId(new Page<>(1, 10), blogId);
        return R.ok(page);
    }
}
