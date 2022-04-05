package com.juno.loginservice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorApi {
    private String code;
    private String msg;
}