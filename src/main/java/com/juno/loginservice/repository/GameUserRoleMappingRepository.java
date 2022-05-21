package com.juno.loginservice.repository;

import com.juno.loginservice.domain.game.GameUserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameUserRoleMappingRepository extends JpaRepository<GameUserRoleMapping, Long> {
}
