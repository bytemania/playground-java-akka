package org.example.playground.model.domain.incident;

import lombok.Data;

@Data
public class Goal {
    private String id;
    private String playerName;
    private Clock clock;
}
