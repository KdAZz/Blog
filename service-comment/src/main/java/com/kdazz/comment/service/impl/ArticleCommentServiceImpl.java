package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.ArticleCommentMapper;
import com.kdazz.comment.pojo.entity.ArticleComment;
import com.kdazz.comment.pojo.vo.ArticleCommentVo;
import com.kdazz.comment.service.IArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements IArticleCommentService {
    @Override
    public IPage<ArticleCommentVo> getCommentByArticleId(Page<ArticleCommentVo> page, Long articleId) {
        return this.baseMapper.getCommentByArticleId(page,articleId, 0L);
    }

}
