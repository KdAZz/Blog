package com.kdazz.common.expception;

import com.kdazz.common.result.R;
import com.kdazz.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
@Order(-1)
public class AuthExceptionHandler {

    /**
     * 用户不存在
     * @param e
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsernameNotFoundException.class)
    public R handleUsernameNotFoundException(UsernameNotFoundException e) {
        return R.failed(ResultCode.USER_NOT_EXIST);
    }

    /**
     * 用户名和密码异常
     * @param e
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidGrantException.class)
    public R handleInvalidGrantException(InvalidGrantException e) {
        return R.failed(ResultCode.USERNAME_OR_PASSWORD_ERROR);
    }

    /**
     * 客户端认证失败
     * @param e
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidClientException.class)
    public R handleInvalidGrantException(InvalidClientException e) {
        return R.failed(ResultCode.CLIENT_AUTHENTICATION_FAILED);
    }


    /**
     * 账户异常(禁用、锁定、过期)
     * @param e
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public R handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return R.failed(e.getMessage());
    }

    /**
     * token 无效或已过期
     * @param e
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({InvalidTokenException.class})
    public R handleInvalidTokenExceptionException(InvalidTokenException e) {
        return R.failed(e.getMessage());
    }

    /**
     * token 无效或已过期
     * @param e
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NoSuchClientException.class})
    public R noSuchClientException(NoSuchClientException e) {
        return R.failed(e.getMessage());
    }


}
