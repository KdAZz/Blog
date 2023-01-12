package com.kdazz.admin.service;

import com.kdazz.admin.pojo.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ISysPermissionService extends IService<SysPermission> {
    boolean refreshPermRolesRules();

    List<SysPermission> listPermRoles();
}
