package org.example.playground.model.domain.incident;

import lombok.Value;

@Value
public class Goal implements Incident {
    String player;
    Clock clock;
}
