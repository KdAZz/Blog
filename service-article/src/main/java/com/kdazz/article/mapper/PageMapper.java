package com.kdazz.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.article.pojo.entity.ArticleContent;
import com.kdazz.article.pojo.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PageMapper extends BaseMapper<ArticleVo> {
    @Select("SELECT a.id,a.author_id,a.content,a.create_time,a.modify_time,a.title,b.username From  article_content a, blog_admin.sys_user b where a.author_id = b.id")
    IPage<ArticleVo> getPage(Page<ArticleVo> page);
}
