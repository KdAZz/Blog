package com.kdazz.admin.api;

import com.kdazz.admin.dto.OAuth2ClientDTO;
import com.kdazz.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "blog-admin", contextId = "oauth-client")
public interface OAuthClientFeignClient {

    @GetMapping("/api/oauth-clients/getOAuth2ClientById")
    R<OAuth2ClientDTO> getOAuth2ClientById(@RequestParam String clientId);
}
