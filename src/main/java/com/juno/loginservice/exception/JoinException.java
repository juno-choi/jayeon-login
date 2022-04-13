package com.juno.loginservice.exception;

import com.juno.loginservice.controller.code.UserCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class JoinException extends CommonException {

    public JoinException(UserCode uc) {
        super(uc.getCode(), uc.getMsg(), uc.getStatus());
    }
}
