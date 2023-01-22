package com.kdazz.article.mapper;

import com.kdazz.article.pojo.entity.ArticleLike;
import org.apache.ibatis.jdbc.SQL;

public class ArticleProvider {
    public String changeLike(ArticleLike articleLike) {
        return new SQL() {{
            UPDATE("article_content");
            if (articleLike.getLikeType()) {
                SET("like_num = like_num + 1");
                SET("dislike_num = dislike_num - 1");
            } else {
                SET("like_num = like_num - 1");
                SET("dislike_num = dislike_num + 1");
            }
            WHERE("id = #{articleId}");
        }}.toString();
    }

    public String changeLikeOne(Boolean type,ArticleLike articleLike) {
        return new SQL() {{
            UPDATE("article_content");
            if (type) {
                if (articleLike.getLikeType()) {
                    SET("like_num = like_num + 1");
                } else {
                    SET("dislike_num = dislike_num + 1");
                }
            }else {
                if (articleLike.getLikeType()) {
                    SET("like_num = like_num - 1");
                } else {
                    SET("dislike_num = dislike_num - 1");
                }
            }
        }}.toString();
    }
}
