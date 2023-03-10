package com.kdazz.comment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;
import com.kdazz.comment.service.IArticleCommentService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@Slf4j
@RequiredArgsConstructor
public class ArticleCommentController {
    private final IArticleCommentService articleCommentService;

    @GetMapping("/{articleId}")
    public R<?> getCommentByArticleId(@PathVariable Long articleId) {
        IPage<ArticleCommentVo> page = articleCommentService.getCommentByArticleId(new Page<>(1, 10), articleId);
        return R.ok(page);
    }

    @PutMapping("/like")
    public R<?> addLike(@RequestBody LikeDto likeDto) {
        return articleCommentService.addLike(likeDto);
    }

    @PutMapping("/dislike")
    public R<?> disLike(@RequestBody LikeDto likeDto) {
        return articleCommentService.disLike(likeDto);
    }

    @DeleteMapping("/like")
    public R<?> removeLike(@RequestParam Long userId, @RequestParam Long articleId) {
        LikeDto likeDto = new LikeDto();
        likeDto.setArticleId(articleId);
        likeDto.setUserId(userId);
        return articleCommentService.removeLike(likeDto);
    }

    @DeleteMapping("/dislike")
    public R<?> removeDislike(@RequestParam Long userId, @RequestParam Long articleId) {
        LikeDto likeDto = new LikeDto();
        likeDto.setArticleId(articleId);
        likeDto.setUserId(userId);
        return articleCommentService.removeDislike(likeDto);
    }
}
