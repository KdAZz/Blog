package com.kdazz.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.comment.pojo.entity.BlogComment;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlogCommentMapper extends BaseMapper<BlogComment> {

    IPage<BlogCommentVo> getCommentByBlogId(Page<BlogCommentVo> page, @Param("blogId")Long blogId, @Param("parentId")Long parentId);
}
