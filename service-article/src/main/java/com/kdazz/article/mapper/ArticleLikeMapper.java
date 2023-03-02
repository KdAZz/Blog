package com.kdazz.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdazz.article.pojo.dto.LikeDto;
import com.kdazz.article.pojo.entity.ArticleLike;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleLikeMapper extends BaseMapper<ArticleLike> {
    @Select("SELECT user_id FROM article_like WHERE article_id = #{articleId}")
    List<String> getListUserList(Long articleId);

    @Insert("INSERT article_like (article_id, user_id)\n" +
            "\tSELECT #{articleId},#{userId}\n" +
            "\t\tWHERE NOT EXISTS (SELECT * FROM article_like WHERE" +
            " article_id = #{articleId} AND user_id = #{userId})\n")
    void insertLike(LikeDto likeDto);
}
