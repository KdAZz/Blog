package com.kdazz.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(value = "update_by",fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
