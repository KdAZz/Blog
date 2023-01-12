package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.CommentMapper;
import com.kdazz.comment.pojo.entity.Comment;
import com.kdazz.comment.service.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {
    @Override
    public List<Comment> getCommentByArticleId(Long articleId) {
        return this.baseMapper.getCommentByArticleId(articleId, 0L);
    }

}
