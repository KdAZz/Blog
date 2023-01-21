package com.kdazz.comment.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kdazz.comment.mapper.BlogCommentMapper;
import com.kdazz.comment.pojo.entity.BlogComment;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import com.kdazz.comment.service.IBlogCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {
    @Override
    public IPage<BlogCommentVo> getCommentByBlogId(Page<BlogCommentVo> page, Long blogId) {
        return this.baseMapper.getCommentByBlogId(page, blogId, 0L);
    }
}
