package com.juno.loginservice.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "game_users")
public class GameUserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id", nullable = false, unique = true, length = 10)
    private String userId;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 150)
    private String pw;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "game_user_role"
    )
    private List<GameRole> roles = new ArrayList<>();


    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public GameUserEntity(@NonNull String userId,@NonNull String name,@NonNull String pw) {
        this.userId = userId;
        this.name = name;
        this.pw = pw;
    }
}
