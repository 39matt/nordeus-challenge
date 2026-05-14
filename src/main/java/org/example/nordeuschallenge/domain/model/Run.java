package org.example.nordeuschallenge.domain.model;

import jakarta.persistence.*;
import org.example.nordeuschallenge.domain.enums.RunStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "runs")
public class Run {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID playerId;

    @Column(nullable = false)
    private int playerHp;

    @Column(nullable = false)
    private int monsterHp;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RunStatus status = RunStatus.IN_PROGRESS;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
