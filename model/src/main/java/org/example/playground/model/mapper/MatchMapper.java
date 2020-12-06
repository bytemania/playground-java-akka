package org.example.playground.model.mapper;

import org.example.playground.model.command.NewMatch;
import org.example.playground.model.domain.Match;
import org.example.playground.model.event.MatchCreated;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    MatchCreated matchToMatchCreated(NewMatch newMatch);

    Match matchCreatedToMatch(MatchCreated matchCreated);
}
