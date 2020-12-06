package org.example.playground.model.mapper;

import org.example.playground.model.domain.incident.Goal;
import org.example.playground.model.event.GoalScored;
import org.example.playground.model.util.fixture.IncidentFixture;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class IncidentMapperTest implements IncidentFixture {

    @Test
    public void shouldTranslateNewGoalInGoalScored() {
        GoalScored goalScored = IncidentMapper.INSTANCE.newGoalToGoalScored(NEW_GOAL_HOME_PLAYER_1);

        assertEquals(NEW_GOAL_HOME_PLAYER_1.getPlayer(), goalScored.getPlayer());
        assertEquals(NEW_GOAL_HOME_PLAYER_1.getClock(), goalScored.getClock());

        assertNull(IncidentMapper.INSTANCE.newGoalToGoalScored(null));
    }

    @Test
    public void shouldTranslateGoalScoredInGoal() {
        Goal goal = IncidentMapper.INSTANCE.goalScoredToGoal(GOAL_SCORED_HOME_PLAYER_1);

        assertEquals(GOAL_SCORED_HOME_PLAYER_1.getPlayer(), goal.getPlayer());
        assertEquals(GOAL_SCORED_HOME_PLAYER_1.getClock(), goal.getClock());

        assertNull(IncidentMapper.INSTANCE.goalScoredToGoal(null));
    }

}
