package com.kdazz.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.kdazz.admin.dto.UserAuthDTO;
import com.kdazz.admin.mapper.SysUserMapper;
import com.kdazz.admin.pojo.entity.SysUser;
import com.kdazz.admin.pojo.entity.SysUserRole;
import com.kdazz.admin.pojo.req.SaveUserReq;
import com.kdazz.admin.service.ISysUserRoleService;
import com.kdazz.admin.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final PasswordEncoder passwordEncoder;
    private final ISysUserRoleService userRoleService;
    @Override
    public UserAuthDTO getByUsername(String username) {
        return this.baseMapper.getByUsername(username);
    }

    @Override
    public String getById(Long authorId){
        return this.baseMapper.selectById(authorId).getUsername();
    }

    @Override
    public void createUser(SaveUserReq req) {
        // 生成密码
        String passwd = passwordEncoder.encode(req.getPassword());
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(req, sysUser);
        sysUser.setPassword(passwd);
        save(sysUser);
        // 维护角色关系
        saveUserRoles(req.getRoleIds(), sysUser.getId());
    }

    private void saveUserRoles(List<Long> roleIds, Long userId) {
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<SysUserRole> sysUserRoles = new ArrayList<>();
            roleIds.forEach(roleId -> {
                sysUserRoles.add(new SysUserRole(userId, roleId));
            });
            userRoleService.saveBatch(sysUserRoles);
        }
    }
}
