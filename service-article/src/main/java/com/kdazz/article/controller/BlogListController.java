package com.kdazz.article.controller;

import com.kdazz.article.service.IBlogListService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogList")
@Slf4j
@RequiredArgsConstructor
public class BlogListController {

    private final IBlogListService service;

    @GetMapping("all")
    public R getAll(){
        return R.ok(service.list());
    }
}
