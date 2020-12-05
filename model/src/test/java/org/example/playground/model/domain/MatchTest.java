package org.example.playground.model.domain;

import org.example.playground.model.command.CreateMatch;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class MatchTest {

    @Test
    public void shouldCreateAnMatch() {
        CreateMatch createMatch = CreateMatch.of(
                "id",
                new Date(),
                Team.of("home", List.of("playerHome1", "playerHome2")),
                Team.of("away", List.of("playerAway1"))
        );

        Match result = Match.createMatch(createMatch);

        //TODO Assert Result
    }
}
