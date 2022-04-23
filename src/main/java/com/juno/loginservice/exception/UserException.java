package com.juno.loginservice.exception;

import com.juno.loginservice.controller.code.UserCode;
import com.juno.loginservice.exception.CommonException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@NoArgsConstructor
@Getter
public class UserException extends CommonException {

    private BindingResult bindingResult;

    public UserException(String code, String msg, HttpStatus status, BindingResult bindingResult) {
        super(code, msg, status);
        this.bindingResult = bindingResult;
    }
    public UserException(UserCode uc, BindingResult bindingResult) {
        super(uc.getCode(), uc.getMsg(), uc.getStatus());
        this.bindingResult = bindingResult;
    }
}
