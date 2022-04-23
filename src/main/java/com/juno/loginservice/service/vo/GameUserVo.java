package com.juno.loginservice.service.vo;

import com.juno.loginservice.entity.GameRole;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class GameUserVo {
    private String userId;
    private String name;
    private String pw;
    private List<GameRole> roles;
    private LocalDateTime createdAt;

    @Builder
    public GameUserVo(@NonNull String userId, @NonNull String name, @NonNull String pw, @NonNull List<GameRole> roles, @NonNull LocalDateTime createdAt) {
        this.userId = userId;
        this.name = name;
        this.pw = pw;
        this.roles = roles;
        this.createdAt = createdAt;
    }
}
