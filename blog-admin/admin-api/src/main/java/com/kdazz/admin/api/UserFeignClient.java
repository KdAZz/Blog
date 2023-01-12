package com.kdazz.admin.api;

import com.kdazz.admin.dto.UserAuthDTO;
import com.kdazz.common.result.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "blog-admin")
public interface UserFeignClient {

    @GetMapping("/api/v1/users/username/{username}")
    R<UserAuthDTO> getUserByUsername(@PathVariable String username);

    @GetMapping("/api/v1/users/member/username/{username}")
    R<UserAuthDTO> getMemberUserByUsername(@PathVariable String username);
}
