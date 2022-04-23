package com.juno.loginservice.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {
    private String access_token;
    private String refresh_token;
}
