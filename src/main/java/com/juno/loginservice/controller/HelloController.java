package com.juno.loginservice.controller;

import com.juno.loginservice.api.CommonApi;
import com.juno.loginservice.api.CommonEnum;
import com.juno.loginservice.exception.CommonException;
import com.juno.loginservice.exception.CommonExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class HelloController {

    private final Environment env;

    @GetMapping("check")
    public ResponseEntity<String> check(HttpServletRequest request){


        return ResponseEntity.ok().body(String.format("hello Login Service Port = %s, %s", env.getProperty("local.server.port"), request.getRemoteHost()));
    }

    @GetMapping("success")
    public ResponseEntity<CommonApi> success(){
        Test test = new Test(1, "test1");

        CommonApi api = new CommonApi(CommonEnum.OK, test);
        return ResponseEntity.ok(api);
    }

    @GetMapping("fail")
    public ResponseEntity<CommonApi> fail() throws Exception{
        Test test = new Test(1, "test1");
        CommonApi api = new CommonApi(CommonEnum.OK, test);

        if(true) throw new CommonException(CommonExceptionEnum.BAD_REQUEST);
        return ResponseEntity.ok(api);
    }

    @Data
    @AllArgsConstructor
    public class Test{
        private Integer idx;
        private String name;
    }
}
