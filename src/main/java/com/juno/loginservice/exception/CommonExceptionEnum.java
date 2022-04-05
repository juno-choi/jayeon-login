package com.juno.loginservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CommonExceptionEnum {
    BAD_REQUEST("400","잘못된 요청", HttpStatus.BAD_REQUEST),
    ;

    CommonExceptionEnum(String code, String msg, HttpStatus status) {
        this.code = code;
        this.msg = msg;
        this.status = status;
    }

    private String code;
    private String msg;
    private HttpStatus status;
}