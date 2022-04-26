package com.juno.loginservice.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class RequestLogin {
    private String userId;
    private String pw;
}
