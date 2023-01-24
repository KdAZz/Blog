package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.BlogListMapper;
import com.kdazz.article.pojo.entity.BlogList;
import com.kdazz.article.pojo.vo.BlogVo;
import com.kdazz.article.service.IBlogListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogListServiceImpl extends ServiceImpl<BlogListMapper, BlogList> implements IBlogListService {

    @Override
    public IPage<BlogVo> getBlogPage(Page<BlogVo> page) {
        return this.baseMapper.getPage(page);
    }
}
