package com.juno.loginservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommonException extends RuntimeException{
    private String code;
    private String msg;
    private HttpStatus status;

    public CommonException(CommonExceptionEnum e) {
        this.code = e.getCode();
        this.msg = e.getMsg();
        this.status = e.getStatus();
    }
}