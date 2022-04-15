package com.juno.loginservice.repository;

import com.juno.loginservice.entity.GameRole;
import org.springframework.data.repository.CrudRepository;

public interface GameRoleRepository extends CrudRepository<GameRole, Long> {
    GameRole findByName(String name);
}
