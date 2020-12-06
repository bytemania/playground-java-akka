package org.example.playground.model.event;

import lombok.Value;
import org.example.playground.model.domain.incident.Clock;

@Value
public class GoalScored {
    String player;
    Clock clock;
}
