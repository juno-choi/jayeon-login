package com.juno.loginservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
@RequiredArgsConstructor
public class HelloController {

    private final Environment env;

    @GetMapping("check")
    public ResponseEntity<String> check(){

        return ResponseEntity.ok().body(String.format("hello Login Service Port = %s", env.getProperty("local.server.port")));
    }
}
