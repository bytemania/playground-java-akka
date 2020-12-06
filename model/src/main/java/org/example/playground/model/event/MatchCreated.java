package org.example.playground.model.event;

import lombok.Value;
import org.example.playground.model.domain.Team;

import java.util.Date;

@Value
public class MatchCreated {
    String id;
    Date startTime;
    Team home;
    Team away;
}
