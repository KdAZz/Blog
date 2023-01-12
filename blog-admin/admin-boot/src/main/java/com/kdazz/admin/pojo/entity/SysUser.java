package com.kdazz.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.List;

@Data
public class SysUser {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String nickname;

    private String mobile;

    private Integer gender;

    private String avatar;

    private String password;

    private String email;

    private Integer status;

    @TableLogic(value = "0", delval = "1")
    private Integer deleted;


    @TableField(exist = false)
    private List<Long> roleIds;

    @TableField(exist = false)
    private String roleNames;

    @TableField(exist = false)
    private List<String> roles;


}
