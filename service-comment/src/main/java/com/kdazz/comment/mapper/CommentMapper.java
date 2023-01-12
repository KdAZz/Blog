package com.kdazz.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdazz.comment.pojo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> getCommentByArticleId(@Param("articleId")Long articleId, @Param("parentId")Long parentId);
}
