package com.juno.loginservice.api;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class CommonApi<T>{
    private String code;
    private String msg;
    private T data;

    public CommonApi(@NonNull String code, @NonNull String msg, @NonNull T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonApi(@NonNull CommonEnum e,@NonNull T data) {
        this.code = e.getCode();
        this.msg = e.getMsg();
        this.data = data;
    }
}
