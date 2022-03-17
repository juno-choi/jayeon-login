package com.juno.loginservice.controller.vo;

import lombok.*;

@AllArgsConstructor
@Getter
@ToString
public class RequestUser {
    private String userId;
    private String pw;
    private String name;
    private String address1;    //우편번호
    private String address2;    //도로명 주소
    private String address3;    //상세 주소
    private String tel;         //전화번호
}
