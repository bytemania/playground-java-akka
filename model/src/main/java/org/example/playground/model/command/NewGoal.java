package org.example.playground.model.command;

import lombok.Value;
import org.example.playground.model.domain.incident.Clock;

@Value
public class NewGoal {
    String player;
    Clock clock;
}
