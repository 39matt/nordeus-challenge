package org.example.nordeuschallenge.dto.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePlayerRequest {
    private String username;
    private String password;
}
