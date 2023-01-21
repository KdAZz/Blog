package com.kdazz.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.comment.pojo.entity.ArticleComment;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {

    IPage<ArticleCommentVo> getCommentByArticleId(Page<ArticleCommentVo> page, @Param("articleId")Long articleId, @Param("parentId")Long parentId);
}
