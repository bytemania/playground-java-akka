package org.example.playground.model.mapper;

import org.example.playground.model.domain.Match;
import org.example.playground.model.domain.Stats;
import org.example.playground.model.event.MatchCreated;
import org.example.playground.model.util.fixture.MatchFixture;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchMapperTest implements MatchFixture {
    @Test
    public void shouldTranslateNewMatchToMatchEvent() {
        MatchCreated matchCreated = MatchMapper.INSTANCE.matchToMatchCreated(NEW_MATCH);

        assertEquals(NEW_MATCH.getId(), matchCreated.getId());
        assertEquals(NEW_MATCH.getStartTime(), matchCreated.getStartTime());
        assertEquals(NEW_MATCH.getHome(), matchCreated.getHome());
        assertEquals(NEW_MATCH.getAway(), matchCreated.getAway());
    }

    @Test
    public void shouldTranslateMatchCreatedToMatch() {
        Match match = MatchMapper.INSTANCE.matchCreatedToMatch(MATCH_CREATED);

        assertEquals(MATCH_CREATED.getId(), match.getId());
        assertEquals(MATCH_CREATED.getStartTime(), match.getStartTime());
        assertEquals(MATCH_CREATED.getHome(), match.getHome());
        assertEquals(MATCH_CREATED.getAway(), match.getAway());
        assertEquals(Stats.create(), match.getStats());
        assertTrue(match.getIncidents().isEmpty());
    }


}
