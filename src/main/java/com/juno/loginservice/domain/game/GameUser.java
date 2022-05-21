package com.juno.loginservice.domain.game;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "game_users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "game_user_id")
public class GameUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_user_id")
    Long gameUserId;

    @Column(name = "user_id", nullable = false, unique = true, length = 10)
    private String userId;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 150)
    private String pw;

    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER)
    @JsonManagedReference(value = "user")
    List<GameUserRoleMapping> gameUserRoleMapping = new ArrayList<>();

    @UpdateTimestamp
    protected LocalDateTime updatedAt = LocalDateTime.now();

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public GameUser(@NonNull String userId, @NonNull String name, @NonNull String pw , Long gameUserId, LocalDateTime updatedAt, LocalDateTime createdAt) {
        this.gameUserId = gameUserId;
        this.userId = userId;
        this.name = name;
        this.pw = pw;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    public void addRole(GameUserRoleMapping roleMapping){
        this.gameUserRoleMapping.add(roleMapping);
    }
}
