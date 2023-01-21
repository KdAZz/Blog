package com.kdazz.article.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.article.mapper.PageMapper;
import com.kdazz.article.pojo.vo.ArticleVo;
import com.kdazz.article.service.IPageService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageServiceImpl extends ServiceImpl<PageMapper, ArticleVo> implements IPageService {

    @Override
    public IPage<ArticleVo> getPageList(Page<ArticleVo> page) {
        return this.baseMapper.getPage(page);
    }
}
