package org.example.playground.model.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.playground.model.domain.incident.Goal;
import org.example.playground.model.domain.incident.Incident;
import org.example.playground.model.event.GoalScored;
import org.example.playground.model.event.MatchCreated;
import org.example.playground.model.mapper.IncidentMapper;
import org.example.playground.model.mapper.MatchMapper;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

@Slf4j
@Data
public class Match {

    private Stats stats = Stats.create();

    private String id;
    private Date startTime;
    private Team home;
    private Team away;
    private Set<Incident> incidents = new TreeSet<>(
            Comparator.comparing((Incident i) -> i.getClock().getPeriod())
                    .thenComparing(i -> i.getClock().getMinutes())
                    .thenComparing(i -> i.getClock().getSeconds()));

    public static Match createMatch(MatchCreated matchCreated) {
        return MatchMapper.INSTANCE.matchCreatedToMatch(matchCreated);
    }

    public void addGoal(GoalScored goalScored) {
        Predicate<Team> findPlayer = team -> team.getPlayers().stream().anyMatch(goalScored.getPlayer()::equals);

        boolean isHomeGoal = findPlayer.test(home);
        boolean isAwayGoal = findPlayer.test(away);

        if (!isHomeGoal && !isAwayGoal) {
            log.warn("Match id '{}', player not found '{}', Goal Ignored", id, goalScored.getPlayer());
            return;
        }

        if (isHomeGoal) {
            stats.homeGoal();
        } else {
            stats.awayGoal();
        }

        Goal goal = IncidentMapper.INSTANCE.goalScoredToGoal(goalScored);
        incidents.add(goal);
    }
}
