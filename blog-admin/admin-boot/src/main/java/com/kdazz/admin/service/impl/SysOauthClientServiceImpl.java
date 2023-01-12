package com.kdazz.admin.service.impl;

import com.kdazz.admin.mapper.SysOauthClientMapper;
import com.kdazz.admin.pojo.entity.SysOauthClient;
import com.kdazz.admin.service.ISysOauthClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SysOauthClientServiceImpl extends ServiceImpl<SysOauthClientMapper, SysOauthClient> implements ISysOauthClientService {

}
