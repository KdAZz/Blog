package com.kdazz.security.conf;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import com.kdazz.common.result.R;
import com.kdazz.common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;


@Configuration
@EnableWebSecurity
@Slf4j
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService sysUserDetailsService;
    private final String realName = ".";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/oauth/**","/test/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint());
    }

    /**
     * 认证管理对象
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 添加自定义认证器
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    /**
     * 设置默认的用户名密码认证授权提供者
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(sysUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 自定义认证异常响应数据
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, e) -> {
            if (e instanceof InsufficientAuthenticationException){
                response.addHeader("WWW-Authenticate", "Basic realm= "+realName);
                response.sendError(org.springframework.http.HttpStatus.UNAUTHORIZED.value(), org.springframework.http.HttpStatus.UNAUTHORIZED.getReasonPhrase());
            }else{
                response.setStatus(HttpStatus.HTTP_OK);
                response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Cache-Control", "no-cache");
                R result = R.failed(ResultCode.CLIENT_AUTHENTICATION_FAILED);
                response.getWriter().print(JSONUtil.toJsonStr(result));
                response.getWriter().flush();
            }
        };
    }

}
