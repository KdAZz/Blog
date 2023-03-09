package com.kdazz.comment.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.comment.pojo.entity.BlogComment;
import com.kdazz.comment.pojo.vo.BlogCommentVo;
import com.kdazz.common.dto.LikeDto;
import com.kdazz.common.result.R;
import org.springframework.stereotype.Service;

@Service
public interface IBlogCommentService extends IService<BlogComment> {
    IPage<BlogCommentVo> getCommentByBlogId(Page<BlogCommentVo> page, Long blogId);

    R<?> addLike(LikeDto likeDto);

    R<?> disLike(LikeDto likeDto);

    R<?> removeLike(LikeDto likeDto);

    R<?> removeDislike(LikeDto likeDto);

    void pushLike(LikeDto likeDto);

    void deleteLike(LikeDto likeDto);
}
