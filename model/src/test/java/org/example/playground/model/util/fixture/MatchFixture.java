package org.example.playground.model.util.fixture;

import org.example.playground.model.command.NewMatch;
import org.example.playground.model.domain.Team;
import org.example.playground.model.event.MatchCreated;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MatchFixture extends Fixture {

    NewMatch NEW_MATCH = new NewMatch(
            UUID.randomUUID().toString(),
            new Date(),
            Team.of(HOME_TEAM, List.of(HOME_PLAYER_1, HOME_PLAYER_2)),
            Team.of(AWAY_TEAM, List.of(AWAY_PLAYER_1)));

    MatchCreated MATCH_CREATED = new MatchCreated(
            UUID.randomUUID().toString(),
            new Date(),
            Team.of(HOME_TEAM, List.of(HOME_PLAYER_1, HOME_PLAYER_2)),
            Team.of(AWAY_TEAM, List.of(AWAY_PLAYER_1)));


}
