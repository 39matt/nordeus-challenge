CREATE TABLE moves (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    amount INT NOT NULL,
    type VARCHAR(20) NOT NULL
);

CREATE TABLE players (
    id UUID PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    xp INT NOT NULL DEFAULT 0,
    level INT NOT NULL DEFAULT 1,
    max_hp INT NOT NULL
);

CREATE TABLE monsters (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    max_hp INT NOT NULL,
    reward_xp INT NOT NULL
);

CREATE TABLE runs (
    id UUID PRIMARY KEY,
    player_id UUID NOT NULL,
    current_floor INT NOT NULL DEFAULT 0,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_runs_player
        FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
);

CREATE TABLE battles (
    id UUID PRIMARY KEY,
    run_id UUID NOT NULL,
    floor_number INT NOT NULL,
    monster_id UUID NOT NULL,
    player_hp INT NOT NULL,
    monster_hp INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    current_turn VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_battles_run
        FOREIGN KEY (run_id) REFERENCES runs(id) ON DELETE CASCADE,
    CONSTRAINT fk_battles_monster
        FOREIGN KEY (monster_id) REFERENCES monsters(id)
);

CREATE TABLE player_equipped_moves (
                                       player_id UUID NOT NULL,
                                       move_id UUID NOT NULL,
                                       PRIMARY KEY (player_id, move_id),
                                       CONSTRAINT fk_player_equipped_moves_player
                                           FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE,
                                       CONSTRAINT fk_player_equipped_moves_move
                                           FOREIGN KEY (move_id) REFERENCES moves(id) ON DELETE CASCADE
);

CREATE TABLE player_available_moves (
                                        player_id UUID NOT NULL,
                                        move_id UUID NOT NULL,
                                        PRIMARY KEY (player_id, move_id),
                                        CONSTRAINT fk_player_available_moves_player
                                            FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE,
                                        CONSTRAINT fk_player_available_moves_move
                                            FOREIGN KEY (move_id) REFERENCES moves(id) ON DELETE CASCADE
);

CREATE TABLE monster_moves (
                               monster_id UUID NOT NULL,
                               move_id UUID NOT NULL,
                               PRIMARY KEY (monster_id, move_id),
                               CONSTRAINT fk_monster_moves_monster
                                   FOREIGN KEY (monster_id) REFERENCES monsters(id) ON DELETE CASCADE,
                               CONSTRAINT fk_monster_moves_move
                                   FOREIGN KEY (move_id) REFERENCES moves(id) ON DELETE CASCADE
);