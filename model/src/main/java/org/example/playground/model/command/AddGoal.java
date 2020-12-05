package org.example.playground.model.command;

import lombok.Value;
import org.example.playground.model.domain.incident.Clock;

@Value(staticConstructor = "of")
public class AddGoal {
    String player;
    Clock clock;
}
