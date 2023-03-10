package com.kdazz.comment.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.comment.pojo.entity.BlogComment;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import com.kdazz.comment.service.IBlogCommentLikeService;
import com.kdazz.comment.service.IBlogCommentService;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogCommentController {
    private final IBlogCommentService blogCommentService;

    private final IBlogCommentLikeService blogCommentLikeService;

    @GetMapping("/{blogId}")
    public R getCommentByBlogId(@PathVariable Long blogId){
        IPage<BlogCommentVo> page = blogCommentService.getCommentByBlogId(new Page<>(1, 10), blogId);
        return R.ok(page);
    }

    @PostMapping
    public R<?> newBlogComment(@RequestBody BlogComment blogComment) {
        return blogCommentService.save(blogComment) ? R.ok("评论成功"): R.failed("评论失败");
    }

    //TODO 待升级
    @DeleteMapping("/{commentId}/{userId}")
    public R<?> deleteBlogComment(@PathVariable Long commentId, @PathVariable Long userId) {
        if (blogCommentLikeService.getById(commentId).getBlogId().equals(userId)) {
            return blogCommentLikeService.removeById(commentId) ? R.ok("删除成功") : R.failed("删除失败，可能已删除");
        }
        return R.failed("当前用户不能删除这条评论");
    }

    @PutMapping("/like")
    public R<?> addLike(@RequestBody LikeDto likeDto) {
        return blogCommentLikeService.addLike(likeDto);
    }

    @PutMapping("/dislike")
    public R<?> disLike(@RequestBody LikeDto likeDto) {
        return blogCommentLikeService.disLike(likeDto);
    }

    @DeleteMapping("/like")
    public R<?> removeLike(@RequestParam Long userId, @RequestParam Long articleId) {
        LikeDto likeDto = new LikeDto();
        likeDto.setArticleId(articleId);
        likeDto.setUserId(userId);
        return blogCommentLikeService.removeLike(likeDto);
    }

    @DeleteMapping("/dislike")
    public R<?> removeDislike(@RequestParam Long userId, @RequestParam Long articleId) {
        LikeDto likeDto = new LikeDto();
        likeDto.setArticleId(articleId);
        likeDto.setUserId(userId);
        return blogCommentLikeService.removeDislike(likeDto);
    }
}
