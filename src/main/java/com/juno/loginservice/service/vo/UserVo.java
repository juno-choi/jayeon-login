package com.juno.loginservice.service.vo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class UserVo {
    private String userId;
    private String name;
    private String pw;
    private LocalDateTime createdAt;

    @Builder
    public UserVo(@NonNull String userId, @NonNull String name, @NonNull String pw, @NonNull LocalDateTime createdAt) {
        this.userId = userId;
        this.name = name;
        this.pw = pw;
        this.createdAt = createdAt;
    }
}
