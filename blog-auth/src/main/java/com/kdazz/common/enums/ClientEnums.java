package com.kdazz.common.enums;

import lombok.Getter;

public enum ClientEnums {
    MEMBER_CLIENT("member","会员客户端"),
    ADMIN_CLIENT("blog","后台客户端");

    @Getter
    private String name;
    @Getter
    private String desc;
    ClientEnums(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
