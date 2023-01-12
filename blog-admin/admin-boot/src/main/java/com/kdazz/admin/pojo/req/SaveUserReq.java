package com.kdazz.admin.pojo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class SaveUserReq {

    private Long id;

    @NotBlank(message = "password 不能为空")
    private String password;
    @NotBlank(message = "username 不能为空")
    private String username;

    @NotBlank(message = "nickname 不能为空")
    private String nickname;

    @NotBlank(message = "mobile 不能为空")
    private String mobile;

    @NotNull(message = "gender 不能为空")
    private Integer gender;

    @NotBlank(message = "email 不能为空")
    private String email;

    @NotNull(message = "status 不能为空")
    private Integer status;

    @Size(min = 1,message = "roleIds 不能为空")
    private List<Long> roleIds;

}
