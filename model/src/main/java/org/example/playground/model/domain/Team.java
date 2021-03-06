package org.example.playground.model.domain;

import lombok.Value;

import java.util.List;

@Value(staticConstructor = "of")
public class Team {
    String name;
    List<String> players;
}
