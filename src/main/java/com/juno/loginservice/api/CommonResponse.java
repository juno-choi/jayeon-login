package com.juno.loginservice.api;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommonResponse<T>{
    private String code;
    private String msg;
    private T data;

    @Builder
    public CommonResponse(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
