package com.kdazz.admin.mapper;

import com.kdazz.admin.pojo.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    List<SysPermission> listPermRoles();
}
