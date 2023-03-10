package com.kdazz.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdazz.comment.pojo.entity.BlogCommentLike;
import com.kdazz.common.dto.LikeDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogCommentLikeMapper extends BaseMapper<BlogCommentLike> {
    @Insert("INSERT blog_comment_like (blog_id, user_id)\n" +
            "\tSELECT #{articleId},#{userId}\n" +
            "\tWHERE NOT EXISTS (SELECT * FROM blog_comment_like WHERE" +
            " blog_id = #{articleId} AND user_id = #{userId})\n")
    void insertLike(LikeDto likeDto);
}
