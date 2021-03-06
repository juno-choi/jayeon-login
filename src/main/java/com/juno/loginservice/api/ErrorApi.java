package com.juno.loginservice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorApi {
    private String code;
    private String msg;
}