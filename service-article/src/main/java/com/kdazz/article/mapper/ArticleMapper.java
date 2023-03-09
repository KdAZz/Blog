package com.kdazz.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.article.pojo.entity.ArticleContent;
import com.kdazz.article.pojo.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleContent> {
    /**
     * 返回分页的文章
     */
    @Select("SELECT\n" +
            "\ta.id,\n" +
            "\ta.author_id,\n" +
            "\ta.content,\n" +
            "\ta.create_time,\n" +
            "\ta.modify_time,\n" +
            "\ta.title,\n" +
            "\tb.username \n" +
            "FROM\n" +
            "\tarticle_content a,\n" +
            "\tblog_admin.sys_user b \n" +
            "WHERE\n" +
            "\ta.author_id = b.id")
    IPage<ArticleVo> getPage(Page<ArticleVo> page);

    @Select("SELECT\n" +
            "\ta.id,\n" +
            "\ta.author_id,\n" +
            "\ta.content,\n" +
            "\ta.create_time,\n" +
            "\ta.modify_time,\n" +
            "\ta.title,\n" +
            "\tb.username\n" +
            "FROM\n" +
            "\tarticle_content a,\n" +
            "\tblog_admin.sys_user b,\n" +
            "\tarticle_content_category c\n" +
            "WHERE\n" +
            "\ta.author_id = b.id\n" +
            "\tAND c.content_id = a.id\n" +
            "\tAND c.category_id = #{categoryId}\n" +
            "GROUP BY\n" +
            "\ta.id")
    IPage<ArticleVo> getArticleByCategory(Page<ArticleVo> page, Long categoryId);
}
