package com.kdazz.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.article.pojo.entity.ArticleContent;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.pojo.vo.ArticleDetailVo;
import com.kdazz.article.pojo.vo.ArticleVo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper extends BaseMapper<ArticleContent> {
    /**
     * 获取文章详细信息
     * @param articleId 文章ID
     */
    @Select("SELECT a.author_id, a.content, a.create_time, a.id, " +
            "a.modify_time, a.title, a.like_num, a.dislike_num, b.username, c.article_num, c.be_concern_num, c.concern_num " +
            "FROM article_content a, blog_admin.sys_user b, blog_admin.user_count c " +
            "WHERE a.author_id = b.id and a.author_id = c.user_id and a.id = #{articleId}")
    ArticleDetailVo getArticleById(@Param("articleId") Long articleId);

    /**
     * 返回分页的文章
     */
    @Select("SELECT a.id,a.author_id,a.content,a.create_time,a.modify_time,a.title, a.like_num, a.dislike_num,b.username From  article_content a, blog_admin.sys_user b where a.author_id = b.id")
    IPage<ArticleVo> getPage(Page<ArticleVo> page);

    /**
     * 改变喜欢与不喜欢的状态
     */
    @UpdateProvider(value = ArticleProvider.class, method = "changeLike")
    void changeLike(ArticleLike articleLike);

    /**
     * 添加或者删除喜欢状态
     */
    @UpdateProvider(value = ArticleProvider.class, method = "changeLikeOne")
    void changeLikeOne(Boolean type ,ArticleLike articleLike);
}
