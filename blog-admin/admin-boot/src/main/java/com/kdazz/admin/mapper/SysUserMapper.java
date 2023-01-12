package com.kdazz.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kdazz.admin.dto.UserAuthDTO;
import com.kdazz.admin.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    UserAuthDTO getByUsername(@Param("userName") String userName);
}
