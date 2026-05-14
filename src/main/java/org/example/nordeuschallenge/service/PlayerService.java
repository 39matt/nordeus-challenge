package org.example.nordeuschallenge.service;

import org.example.nordeuschallenge.domain.model.Move;
import org.example.nordeuschallenge.domain.model.Player;
import org.example.nordeuschallenge.dto.Player.CreatePlayerRequest;
import org.example.nordeuschallenge.dto.Player.PlayerResponse;
import org.example.nordeuschallenge.exceptions.ResourceNotFoundException;
import org.example.nordeuschallenge.exceptions.UsernameAlreadyExistsException;
import org.example.nordeuschallenge.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlayerService  {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    private PlayerResponse toResponse(Player player) {
        PlayerResponse response = new PlayerResponse();
        response.setId(player.getId());
        response.setUsername(player.getUsername());
        response.setXp(player.getXp());
        response.setLevel(player.getLevel());
        response.setMaxHp(player.getMaxHp());
        response.setEquippedMoveIds(extractMoveIds(player.getEquippedMoves()));
        response.setAvailableMoveIds(extractMoveIds(player.getAvailableMoves()));
        return response;
    }
    private Set<UUID> extractMoveIds(Set<Move> moves) {
        if (moves == null) {
            return Set.of();
        }

        return moves.stream()
                .map(Move::getId)
                .collect(Collectors.toSet());
    }

    @Transactional
    public PlayerResponse createPlayer(CreatePlayerRequest request) {
        if(playerRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists" + request.getUsername());
        }

        Player player = new Player();
        player.setUsername(request.getUsername());
        player.setPassword(request.getPassword());
        player.setXp(0);
        player.setLevel(1);
        player.setMaxHp(100);

        Player saved = playerRepository.save(player);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public PlayerResponse getPlayerById(UUID id) {
        return toResponse(
                playerRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Player not found: " + id))
        );
    }

    @Transactional(readOnly = true)
    public List<PlayerResponse> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
