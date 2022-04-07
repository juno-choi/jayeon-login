package com.juno.loginservice.controller.vo;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Getter
@ToString
public class RequestUser {
    @NotEmpty(message = "아이디는 비어있을 수 없습니다.")
    @Length(min = 4, max = 12, message = "아이디는 4~12 글자입니다.")
    private String userId;
    @NotEmpty(message = "비밀번호는 비어있을 수 없습니다.")
    @Length(min = 6, max = 12, message = "비밀번호는 6~12 글자입니다.")
    private String pw;
    @NotEmpty(message = "이름은 비어있을 수 없습니다.")
    private String name;
    @NotEmpty(message = "우편번호는 비어있을 수 없습니다.")
    private String address1;    //우편번호
    @NotEmpty(message = "주소는 비어있을 수 없습니다.")
    private String address2;    //주소
    @NotEmpty(message = "상세주소는 비어있을 수 없습니다.")
    private String address3;    //상세 주소
    @NotEmpty(message = "연락처는 비어있을 수 없습니다.")
    private String tel;         //연락처
}
