package org.example.playground.model.util.fixture;

import org.example.playground.model.command.NewGoal;
import org.example.playground.model.domain.incident.Clock;
import org.example.playground.model.domain.incident.Period;
import org.example.playground.model.event.GoalScored;

public interface IncidentFixture extends Fixture {

    NewGoal NEW_GOAL_HOME_PLAYER_1 = new NewGoal(HOME_PLAYER_1, new Clock(Period.FIRST_HALF, 33, 45));

    GoalScored GOAL_SCORED_HOME_PLAYER_1 = new GoalScored(HOME_PLAYER_1, new Clock(Period.FIRST_HALF, 33, 45));

    GoalScored GOAL_SCORED_HOME_PLAYER_2 = new GoalScored(HOME_PLAYER_2, new Clock(Period.SECOND_HALF, 17, 36));

    GoalScored GOAL_SCORED_AWAY_PLAYER_1 = new GoalScored(AWAY_PLAYER_1, new Clock(Period.SECOND_HALF, 15, 5));

}
