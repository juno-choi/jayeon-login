package com.juno.loginservice.repository;

import com.juno.loginservice.domain.game.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Long> {
    GameUser findByUserId(String userId);
}
