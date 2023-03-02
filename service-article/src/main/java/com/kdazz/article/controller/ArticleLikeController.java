package com.kdazz.article.controller;

import com.kdazz.article.pojo.dto.LikeDto;
import com.kdazz.article.service.IArticleLikeService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articleLike")
@Slf4j
@RequiredArgsConstructor
public class ArticleLikeController {
    private final IArticleLikeService articleLikeService;

    @GetMapping("/{articleId}")
    public R<?> getArticleLike(@PathVariable("articleId") Long articleId) {
        return R.ok(articleLikeService.getLikeCount(articleId));
    }

    @PutMapping("/addLike")
    public R<?> addLike(@RequestBody LikeDto likeDto){
            return articleLikeService.addLike(likeDto);
    }

    @PutMapping("/removeLike")
    public R<?> removeLike(@RequestBody LikeDto likeDto) {
        return articleLikeService.removeLike(likeDto);
    }

}
