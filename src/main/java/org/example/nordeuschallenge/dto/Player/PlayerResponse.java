package org.example.nordeuschallenge.dto.Player;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class PlayerResponse {
    private UUID id;
    private String username;
    private int xp;
    private int level;
    private int maxHp;
    private Set<UUID> equippedMoveIds;
    private Set<UUID> availableMoveIds;
}
