package com.juno.loginservice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidError {
    private String field;
    private String msg;
}
