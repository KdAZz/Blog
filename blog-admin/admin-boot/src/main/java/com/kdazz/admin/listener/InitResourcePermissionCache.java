package com.kdazz.admin.listener;

import com.kdazz.admin.service.ISysPermissionService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InitResourcePermissionCache implements CommandLineRunner {

    private ISysPermissionService iSysPermissionService;

    @Override
    public void run(String... args) {
        iSysPermissionService.refreshPermRolesRules();
    }
}
