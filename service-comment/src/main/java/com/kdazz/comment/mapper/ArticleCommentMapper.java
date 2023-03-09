package com.kdazz.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.comment.pojo.entity.ArticleComment;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;
import com.kdazz.common.dto.LikeDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    IPage<ArticleCommentVo> getCommentByArticleId(Page<ArticleCommentVo> page, @Param("articleId")Long articleId, @Param("parentId")Long parentId);

    @Insert("INSERT article_comment_like (article_id, user_id)\n" +
            "\tSELECT #{articleId},#{userId}\n" +
            "\t\tWHERE NOT EXISTS (SELECT * FROM article_like WHERE" +
            " article_id = #{articleId} AND user_id = #{userId})\n")
    void insertLike(LikeDto likeDto);
}
