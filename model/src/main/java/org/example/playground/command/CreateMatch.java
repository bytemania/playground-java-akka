package org.example.playground.command;

import lombok.Value;

import java.util.Date;
import java.util.List;

@Value(staticConstructor = "of")
public class CreateMatch {
    String id;
    Date startTime;
    String homeTeam;
    List<String> homePlayers;
    String awayTeam;
    List<String> awayPlayers;
}
