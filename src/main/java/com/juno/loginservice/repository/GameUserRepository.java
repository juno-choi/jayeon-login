package com.juno.loginservice.repository;

import com.juno.loginservice.entity.GameUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUserRepository extends CrudRepository<GameUserEntity, Long> {
    GameUserEntity findByUserId(String userId);
}
