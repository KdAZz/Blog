package com.kdazz.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdazz.article.pojo.entity.ArticleContent;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleContent> {
}
