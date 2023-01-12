package com.kdazz.admin.service;


import com.kdazz.admin.dto.UserAuthDTO;
import com.kdazz.admin.pojo.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kdazz.admin.pojo.req.SaveUserReq;

public interface ISysUserService extends IService<SysUser> {

    UserAuthDTO getByUsername(String username);

    void createUser(SaveUserReq req);
}
