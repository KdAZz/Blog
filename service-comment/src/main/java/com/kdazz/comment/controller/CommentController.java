package com.kdazz.comment.controller;

import com.kdazz.comment.pojo.entity.Comment;
import com.kdazz.comment.service.ICommentService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;

    @GetMapping("/articleId/{articleId}")
    public R getCommentByArticleId(@PathVariable Long articleId){
        List<Comment> list = commentService.getCommentByArticleId(articleId);
        return R.ok(list);
    }
}
