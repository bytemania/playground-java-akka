package org.example.playground.model.mapper;

import org.example.playground.model.command.NewGoal;
import org.example.playground.model.domain.incident.Goal;
import org.example.playground.model.event.GoalScored;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface IncidentMapper {
    IncidentMapper INSTANCE = Mappers.getMapper(IncidentMapper.class);

    GoalScored newGoalToGoalScored(NewGoal newGoal);

    Goal goalScoredToGoal(GoalScored goalScored);
}
