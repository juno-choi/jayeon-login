package com.juno.loginservice.repository;

import com.juno.loginservice.domain.game.GameRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRoleRepository extends JpaRepository<GameRole, Long> {
    GameRole findByName(String name);
}
