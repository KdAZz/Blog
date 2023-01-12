package com.kdazz;

import com.kdazz.common.result.R;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RestController
@RequestMapping("/oauth")
@AllArgsConstructor
@Slf4j
public class AuthController {
    private final KeyPair keyPair;
    private final TokenEndpoint tokenEndpoint;
    @PostMapping("/token")
    public Object postAccessToken(
            Principal principal,
            @RequestParam Map<String,String> parameters
    ) throws HttpRequestMethodNotSupportedException {
        DefaultOAuth2AccessToken accessToken = (DefaultOAuth2AccessToken) tokenEndpoint.postAccessToken(principal, parameters).getBody();
        return R.ok(accessToken);
    }
    @GetMapping("/public-key")
    public Map<String, Object> getPublicKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}