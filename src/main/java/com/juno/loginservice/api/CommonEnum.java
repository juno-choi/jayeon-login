package com.juno.loginservice.api;

import lombok.Getter;

@Getter
public enum CommonEnum {
    OK("200", "정상실행"),
    FAIL("500", "알수 없는 에러");
    ;

    CommonEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
}
