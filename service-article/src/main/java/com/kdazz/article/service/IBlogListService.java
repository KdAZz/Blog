package com.kdazz.article.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.article.pojo.entity.BlogList;
import com.kdazz.article.pojo.vo.BlogVo;

public interface IBlogListService extends IService<BlogList> {

    IPage<BlogVo> getBlogPage(Page<BlogVo> page);
}
