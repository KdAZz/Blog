package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.BlogListMapper;
import com.kdazz.article.pojo.entity.BlogList;
import com.kdazz.article.service.IBlogListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogListImplement extends ServiceImpl<BlogListMapper, BlogList> implements IBlogListService {

}
