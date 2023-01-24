package com.kdazz.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdazz.article.pojo.entity.ArticleLike;
import com.kdazz.article.pojo.dto.ArticleLikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

@Mapper
public interface ArticleLikeMapper extends BaseMapper<ArticleLike> {
    @Select("SELECT like_type, COUNT(*) FROM article_like WHERE article_id = #{articleId} GROUP BY like_type")
    @Results({
            @Result(column = "like_type", property = "likeType", jdbcType = JdbcType.BIT),
            @Result(column = "COUNT(*)", property = "count", jdbcType = JdbcType.BIGINT)
    })
    List<ArticleLikeDto> getLikeCount(Long articleId);
}
