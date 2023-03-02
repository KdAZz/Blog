package com.kdazz.article.service;

import com.kdazz.common.result.R;

public interface IArticleCategoryService {
    R getArticleCategory();

    R<?> getArticleCategoryById(Long articleId);
}
