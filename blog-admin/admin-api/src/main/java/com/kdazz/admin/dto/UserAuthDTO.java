package com.kdazz.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserAuthDTO {

    private Long userId;

    private String username;

    private String password;

    private Integer status;

    private List<String> roles;
}
