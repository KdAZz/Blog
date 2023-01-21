package com.kdazz.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.article.service.IArticleService;
import com.kdazz.article.service.IPageService;
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

    private final IPageService pageService;
    @GetMapping("/all")
    public R getAll(){
        return R.ok(articleService.list());
    }

    @GetMapping("/page")
    public R getPage(@RequestParam(required = false, defaultValue = "1") int page,
                     @RequestParam(required = false, defaultValue = "10") int pageSize
    ){
        return R.ok(pageService.getPageList(new Page<>(page,pageSize)));
    }
}
