package com.juno.loginservice.api;

import lombok.Getter;

@Getter
public class ErrorListApi<T> extends ErrorApi{
    private T errors;

    public ErrorListApi(String code, String msg, T errors) {
        super(code, msg);
        this.errors = errors;
    }
}
