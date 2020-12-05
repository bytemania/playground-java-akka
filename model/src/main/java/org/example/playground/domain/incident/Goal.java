package org.example.playground.domain.incident;

import lombok.Data;

@Data
public class Goal implements Incident {
    private final String id;
    private final String playerName;
    private final Clock clock;
}
