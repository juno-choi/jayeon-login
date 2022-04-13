package com.juno.loginservice.controller.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserCode {
    BAD_REQUEST("400", "잘못된 입력값 존재", HttpStatus.BAD_REQUEST),
    EXIST_USER("401", "이미 존재하는 아이디", HttpStatus.BAD_REQUEST),
    ;
    private String code;
    private String msg;
    private HttpStatus status;

    UserCode(String code, String msg, HttpStatus status) {
        this.code = code;
        this.msg = msg;
        this.status = status;
    }
}
