package com.kdazz.article.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.article.service.IBlogListService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
@Slf4j
@RequiredArgsConstructor
public class BlogListController {

    private final IBlogListService blogListService;

    @GetMapping("/page")
    public R<?> getPage(@RequestParam(required = false, defaultValue = "1") int page,
                        @RequestParam(required = false, defaultValue = "10") int pageSize
    ) {
        return R.ok(blogListService.getBlogPage(new Page<>(page, pageSize)));
    }
}
