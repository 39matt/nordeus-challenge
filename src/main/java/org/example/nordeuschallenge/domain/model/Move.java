package org.example.nordeuschallenge.domain.model;

import jakarta.persistence.*;
import org.example.nordeuschallenge.domain.enums.MoveType;

import java.util.UUID;

@Entity
@Table(name = "moves")
public class Move {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MoveType type;
}
