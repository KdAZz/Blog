package com.kdazz.article.pojo.vo;

import com.kdazz.article.pojo.entity.BlogList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BlogVo extends BlogList {
    private String userName;
}
