package com.kdazz.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;

@Data
public class SysRole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    /**
    * id
    */
    private Long id;

    /**
    * 角色名称
    */
    private String name;

    /**
    * 角色编码
    */
    private String code;

    /**
    * 显示顺序
    */
    private Integer sort;

    /**
    * 角色状态：0-正常；1-停用
    */
    private int status;

    /**
    * 逻辑删除标识：0-未删除；1-已删除
    */
    @TableLogic(value = "0", delval = "1")
    private int deleted;

    public SysRole() {}
}