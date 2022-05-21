package com.juno.loginservice.domain.game;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game_user_role_mapping")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class GameUserRoleMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_user_id")
    @JsonBackReference(value = "user")
    private GameUser gameUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_role_id")
    private GameRole gameRole;

    @Builder
    public GameUserRoleMapping(GameUser gameUser, GameRole gameRole) {
        this.gameUser = gameUser;
        this.gameRole = gameRole;
    }
}
