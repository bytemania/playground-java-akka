package org.example.playground.command;

import lombok.Value;
import org.example.playground.domain.incident.Clock;

@Value(staticConstructor = "of")
public class AddGoal {
    String player;
    Clock clock;
}
