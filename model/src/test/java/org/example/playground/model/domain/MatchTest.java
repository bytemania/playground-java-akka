package org.example.playground.model.domain;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.example.playground.model.domain.incident.Clock;
import org.example.playground.model.domain.incident.Incident;
import org.example.playground.model.domain.incident.Period;
import org.example.playground.model.event.GoalScored;
import org.example.playground.model.mapper.IncidentMapper;
import org.example.playground.model.util.MemoryAppender;
import org.example.playground.model.util.fixture.IncidentFixture;
import org.example.playground.model.util.fixture.MatchFixture;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MatchTest implements MatchFixture, IncidentFixture {

    MemoryAppender memoryAppender;
    private Match match;

    @Before
    public void setup() {
        Logger logger = (Logger) LoggerFactory.getLogger("org.example.playground.model.domain.Match");
        memoryAppender = new MemoryAppender();
        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
        logger.setLevel(Level.DEBUG);
        logger.addAppender(memoryAppender);
        memoryAppender.start();

        match = Match.createMatch(MATCH_CREATED);
    }

    @Test
    public void shouldCreateAnMatch() {
        assertEquals(MATCH_CREATED.getId(), match.getId());
        assertEquals(MATCH_CREATED.getStartTime(), match.getStartTime());
        assertEquals(MATCH_CREATED.getHome(), match.getHome());
        assertEquals(MATCH_CREATED.getAway(), match.getAway());
        assertEquals(Stats.create(), match.getStats());
        assertTrue(match.getIncidents().isEmpty());
    }

    @Test
    public void shouldLogAWarningForInvalidPlayer() {
        GoalScored GOAL_SCORED_INVALID_PLAYER = new GoalScored("invalidPlayer1", new Clock(Period.FIRST_HALF, 33, 45));
        match.addGoal(GOAL_SCORED_INVALID_PLAYER);

        assertEquals(0, match.getStats().getHomeGoals());
        assertEquals(0, match.getStats().getAwayGoals());
        assertTrue(match.getIncidents().isEmpty());

        assertEquals(1, memoryAppender.getSize());
        assertEquals(Level.WARN, memoryAppender.getLoggedEvents().get(0).getLevel());
        assertEquals("Match id '" + match.getId() + "', player not found 'invalidPlayer1', Goal Ignored",
                memoryAppender.getLoggedEvents().get(0).getFormattedMessage());
    }

    @Test
    public void shouldProcessHomeGoal() {
        match.addGoal(GOAL_SCORED_HOME_PLAYER_1);

        assertEquals(1, match.getStats().getHomeGoals());
        assertEquals(0, match.getStats().getAwayGoals());
        assertEquals(1, match.getIncidents().size());
        assertEquals(IncidentMapper.INSTANCE.goalScoredToGoal(GOAL_SCORED_HOME_PLAYER_1), match.getIncidents().iterator().next());
    }

    @Test
    public void shouldProcessAwayGoal() {
        match.addGoal(GOAL_SCORED_AWAY_PLAYER_1);

        assertEquals(0, match.getStats().getHomeGoals());
        assertEquals(1, match.getStats().getAwayGoals());
        assertEquals(1, match.getIncidents().size());
        assertEquals(IncidentMapper.INSTANCE.goalScoredToGoal(GOAL_SCORED_AWAY_PLAYER_1), match.getIncidents().iterator().next());
    }

    @Test
    public void shouldProcessMultipleIncidents() {
        match.addGoal(GOAL_SCORED_HOME_PLAYER_2);
        match.addGoal(GOAL_SCORED_AWAY_PLAYER_1);
        match.addGoal(GOAL_SCORED_HOME_PLAYER_1);

        Iterator<Incident> it = match.getIncidents().iterator();
        assertEquals(IncidentMapper.INSTANCE.goalScoredToGoal(GOAL_SCORED_HOME_PLAYER_1), it.next());
        assertEquals(IncidentMapper.INSTANCE.goalScoredToGoal(GOAL_SCORED_AWAY_PLAYER_1), it.next());
        assertEquals(IncidentMapper.INSTANCE.goalScoredToGoal(GOAL_SCORED_HOME_PLAYER_2), it.next());

        assertEquals(2, match.getStats().getHomeGoals());
        assertEquals(1, match.getStats().getAwayGoals());
    }

}
