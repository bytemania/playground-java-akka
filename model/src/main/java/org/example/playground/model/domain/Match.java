package org.example.playground.model.domain;

import lombok.Data;
import org.example.playground.model.command.CreateMatch;
import org.example.playground.model.domain.incident.Incident;
import org.example.playground.model.mapper.MatchMapper;

import java.util.Date;
import java.util.List;

@Data
public class Match {
    private String id;
    private Date startTime;

    public static Match createMatch(CreateMatch createMatch) {
        return MatchMapper.INSTANCE.createMatchToMatch(createMatch);
    }

    private Team home;
    private Team away;
    private List<Incident> incidents;


}
