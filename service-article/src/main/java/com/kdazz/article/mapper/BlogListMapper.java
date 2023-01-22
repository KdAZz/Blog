package com.kdazz.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kdazz.article.pojo.entity.BlogList;
import com.kdazz.article.pojo.vo.BlogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlogListMapper extends BaseMapper<BlogList> {
    @Select("SELECT a.id,a.author_id,a.content,a.create_time,a.comment_count,a.forward_count, " +
            "a.like_num, a.dislike_num,b.username From blog_list a, blog_admin.sys_user b where a.author_id = b.id")
    IPage<BlogVo> getPage(Page<BlogVo> page);
}
