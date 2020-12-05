package org.example.playground.model.command;

import lombok.Value;
import org.example.playground.model.domain.Team;

import java.util.Date;

@Value(staticConstructor = "of")
public class CreateMatch {
    String id;
    Date startTime;
    Team home;
    Team away;
}
