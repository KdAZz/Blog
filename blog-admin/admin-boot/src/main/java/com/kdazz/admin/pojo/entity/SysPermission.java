package com.kdazz.admin.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class SysPermission {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private Long menuId;

    private String urlPerm;

    // 有权限的角色编号集合
    @TableField(exist = false)
    private List<String> roles;

}
