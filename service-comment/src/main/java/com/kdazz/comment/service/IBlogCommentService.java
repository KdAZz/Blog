package com.kdazz.comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.comment.pojo.entity.BlogComment;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import org.springframework.stereotype.Service;

@Service
public interface IBlogCommentService extends IService<BlogComment> {
    IPage<BlogCommentVo> getCommentByBlogId(Page<BlogCommentVo> page, Long blogId);
}
