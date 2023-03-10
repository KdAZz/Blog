package com.kdazz.comment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.comment.pojo.entity.ArticleComment;
import com.kdazz.comment.pojo.entity.BlogComment;
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

    @PostMapping
    public R<?> newArticleComment(@RequestBody ArticleComment articleComment) {
        return articleCommentService.save(articleComment) ? R.ok("评论成功"): R.failed("评论失败");
    }

    //TODO 待升级
    @DeleteMapping("/{commentId}/{userId}")
    public R<?> deleteArticleComment(@PathVariable Long commentId, @PathVariable Long userId) {
        if (articleCommentService.getById(commentId).getArticleId().equals(userId)) {
            return articleCommentService.removeById(commentId) ? R.ok("删除成功") : R.failed("删除失败，可能已删除");
        }
        return R.failed("当前用户不能删除这条评论");
    }

    /**
     * add likes
     */
    @PutMapping("/like")
    public R<?> addLike(@RequestBody LikeDto likeDto) {
        return articleCommentService.addLike(likeDto);
    }

    @PutMapping("/dislike")
    public R<?> disLike(@RequestBody LikeDto likeDto) {
        return articleCommentService.disLike(likeDto);
    }

    /**
     * remove likes
     */
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
