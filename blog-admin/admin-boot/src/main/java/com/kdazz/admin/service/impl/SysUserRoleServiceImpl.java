package com.kdazz.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.kdazz.admin.mapper.SysUserRoleMapper;
import com.kdazz.admin.pojo.entity.SysUserRole;
import com.kdazz.admin.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Override
    public List<Long> selectRoleIds(Long userId) {
        List<SysUserRole> userRoles = lambdaQuery().eq(SysUserRole::getUserId, userId).list();
        if (CollectionUtil.isNotEmpty(userRoles)) {
            return userRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public void deleteByUserId(Long userId) {
        this.baseMapper.delete(lambdaQuery().eq(SysUserRole::getUserId,userId).getWrapper());
    }
}
