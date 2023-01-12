package com.kdazz.admin.dto;

import lombok.Data;


@Data
public class OAuth2ClientDTO {

    private String clientId;

    private String clientSecret;

    private String resourceIds;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

}
