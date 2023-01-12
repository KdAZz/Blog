package com.kdazz.article.service.impl;

import com.kdazz.article.mapper.BlogListMapper;
import com.kdazz.article.service.IBlogListService;
import com.kdazz.pojo.entity.BlogList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogListImplement implements IBlogListService {

    private final BlogListMapper blogListMapper;
    @Override
    public List<BlogList> getList() {
        return blogListMapper.selectList(null);
    }
}
