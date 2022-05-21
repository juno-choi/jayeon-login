package com.juno.loginservice.domain.game;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_role_id")
    private Long gameRoleId;
    private String name;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    private List<GameUserRoleMapping> gameUserRoleMappings = new ArrayList<>();

    @Override
    public String getAuthority() {
        return name;
    }

    @Builder
    public GameRole(Long gameRoleId, String name) {
        this.gameRoleId = gameRoleId;
        this.name = name;
    }
}
