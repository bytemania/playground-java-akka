package org.example.playground.model.mapper;

import org.example.playground.model.command.CreateMatch;
import org.example.playground.model.domain.Match;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    Match createMatchToMatch(CreateMatch createMatch);
}
