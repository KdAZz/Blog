package com.kdazz.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.kdazz.admin.dto.OAuth2ClientDTO;
import com.kdazz.admin.pojo.entity.SysOauthClient;
import com.kdazz.admin.service.ISysOauthClientService;
import com.kdazz.common.result.R;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/oauth-clients")
@Slf4j
@AllArgsConstructor
@RestController
public class OauthClientController {
    private ISysOauthClientService iSysOauthClientService;

    @GetMapping("/getOAuth2ClientById")
    public R<OAuth2ClientDTO> getOAuth2ClientById(@RequestParam String clientId) {
        SysOauthClient client = iSysOauthClientService.getById(clientId);
        Assert.notNull(client, "OAuth2 客户端不存在");
        OAuth2ClientDTO oAuth2ClientDTO = new OAuth2ClientDTO();
        BeanUtil.copyProperties(client, oAuth2ClientDTO);
        return R.ok(oAuth2ClientDTO);
    }
}
