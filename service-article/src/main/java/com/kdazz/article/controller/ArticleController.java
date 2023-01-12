package com.kdazz.article.controller;

import com.kdazz.article.service.IArticleService;
import com.kdazz.common.result.R;
import com.kdazz.pojo.entity.Article;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
@Slf4j
@RequiredArgsConstructor
public class ArticleController {

    private final IArticleService articleService;

    @GetMapping("all")
    public R getAll(){
        return R.ok(articleService.getArticle());
    }
}
