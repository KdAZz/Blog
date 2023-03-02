package com.kdazz.article.controller;

import com.kdazz.article.service.IArticleCategoryService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final IArticleCategoryService categoryService;
    @GetMapping("")
    public R getCategoryList(){
        return categoryService.getArticleCategory();
    }
}
