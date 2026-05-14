package org.example.nordeuschallenge.domain.model;

import jakarta.persistence.*;
import org.example.nordeuschallenge.domain.enums.BattleStatus;
import org.example.nordeuschallenge.domain.enums.Turn;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "battles")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID runId;

    @Column(nullable = false)
    private int battleIndex;

    @Column(nullable = false)
    private UUID monsterId;

    @Column(nullable = false)
    private int playerHp;

    @Column(nullable = false)
    private int monsterHp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BattleStatus status = BattleStatus.IN_PROGRESS;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Turn turn = Turn.PLAYER;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
