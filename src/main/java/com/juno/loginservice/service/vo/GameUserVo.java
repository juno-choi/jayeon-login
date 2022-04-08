package com.juno.loginservice.service.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GameUserVo {
    private String userId;
    private String name;
    private String pw;
    private LocalDateTime createdAt;

    @Builder
    public GameUserVo(@NonNull String userId, @NonNull String name, @NonNull String pw, @NonNull LocalDateTime createdAt) {
        this.userId = userId;
        this.name = name;
        this.pw = pw;
        this.createdAt = createdAt;
    }
}
