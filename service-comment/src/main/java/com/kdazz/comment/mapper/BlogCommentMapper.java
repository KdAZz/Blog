package com.kdazz.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.comment.pojo.entity.BlogComment;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import com.kdazz.common.dto.LikeDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogCommentMapper extends BaseMapper<BlogComment> {

    IPage<BlogCommentVo> getCommentByBlogId(Page<BlogCommentVo> page, @Param("blogId")Long blogId, @Param("parentId")Long parentId);

    @Insert("INSERT blog_comment_like (article_id, user_id)\n" +
            "\tSELECT #{articleId},#{userId}\n" +
            "\t\tWHERE NOT EXISTS (SELECT * FROM article_like WHERE" +
            " article_id = #{articleId} AND user_id = #{userId})\n")
    void insertLike(LikeDto likeDto);
}
