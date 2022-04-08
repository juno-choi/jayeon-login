package com.juno.loginservice.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestGameUser {
    @NotEmpty(message = "아이디는 비어있을 수 없습니다.")
    @Length(min = 4, max = 12, message = "아이디는 4~12 글자입니다.")
    private String userId;
    @NotEmpty(message = "비밀번호는 비어있을 수 없습니다.")
    @Length(min = 6, max = 12, message = "비밀번호는 6~12 글자입니다.")
    private String pw;
    @NotEmpty(message = "비밀번호 재입력은 비어있을 수 없습니다.")
    @Length(min = 6, max = 12, message = "비밀번호는 6~12 글자입니다.")
    private String rePw;
    @NotEmpty(message = "이름은 비어있을 수 없습니다.")
    private String name;
}
