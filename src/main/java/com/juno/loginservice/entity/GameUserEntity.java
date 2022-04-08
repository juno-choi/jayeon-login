package com.juno.loginservice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "game_users")
public class GameUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id", nullable = false, unique = true, length = 10)
    private String userId;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 150)
    private String pw;

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public GameUserEntity(@NonNull String userId,@NonNull String name,@NonNull String pw) {
        this.userId = userId;
        this.name = name;
        this.pw = pw;
    }
}
