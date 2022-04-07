package com.juno.loginservice.advice;

import com.juno.loginservice.api.ErrorApi;
import com.juno.loginservice.api.ValidError;
import com.juno.loginservice.api.ErrorListApi;
import com.juno.loginservice.exception.CommonException;
import com.juno.loginservice.service.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestControllerAdvice
public class CommonAdvice {
    @ExceptionHandler(CommonException.class)
    public ResponseEntity commonExceptionAdvice(CommonException ce){
        ErrorApi error = new ErrorApi(ce.getCode(), ce.getMsg());
        return ResponseEntity.status(ce.getStatus()).body(error);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> userExceptionAdvice(UserException ue){

        BindingResult bindingResult = ue.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<ValidError> errorApiList = new LinkedList<>();
        for(FieldError e: fieldErrors){
            String field = e.getField();
            String defaultMessage = e.getDefaultMessage();
            ValidError errorApi = new ValidError(field, defaultMessage);
            errorApiList.add(errorApi);
        }

        ErrorListApi error = new ErrorListApi(ue.getCode(), ue.getMsg(), errorApiList);
        return ResponseEntity.status(ue.getStatus()).body(error);
    }
}