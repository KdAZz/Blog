package com.kdazz.admin.controller;

import com.kdazz.admin.dto.UserAuthDTO;
import com.kdazz.admin.pojo.req.SaveUserReq;
import com.kdazz.admin.service.ISysUserService;
import com.kdazz.common.result.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final ISysUserService iSysUserService;

    private final ISysUserService sysUserService;
    @GetMapping("/username/{username}")
    public R<UserAuthDTO> getUserByUsername(@PathVariable String username) {
        UserAuthDTO user = iSysUserService.getByUsername(username);
        return R.ok(user);
    }

    @PostMapping
    public R createUser(@Validated @RequestBody SaveUserReq req) {
        sysUserService.createUser(req);
        return R.ok();
    }
}
