package org.example.playground.domain;

import lombok.Data;
import org.example.playground.domain.incident.Incident;

import java.util.Date;
import java.util.List;

@Data
public class Match {

    private final String id;
    private Team home;
    private Team away;
    private Date startTime;
    private List<Incident> incidents;

}
