package org.example.nordeuschallenge.repository;

import org.example.nordeuschallenge.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    boolean existsByUsername(String username);
}
