package com.kdazz.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdazz.comment.pojo.entity.ArticleCommentLike;
import com.kdazz.common.dto.LikeDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleCommentLikeMapper extends BaseMapper<ArticleCommentLike> {
    @Insert("INSERT article_comment_like (article_id, user_id)\n" +
            "\tSELECT #{articleId},#{userId}\n" +
            "\tWHERE NOT EXISTS (SELECT * FROM article_comment_like WHERE" +
            " article_id = #{articleId} AND user_id = #{userId})\n")
    void insertLike(LikeDto likeDto);
}
