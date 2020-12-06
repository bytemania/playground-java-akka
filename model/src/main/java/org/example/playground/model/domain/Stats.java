package org.example.playground.model.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Stats {

    public static Stats create() {
        return new Stats();
    }

    private int homeGoals;
    private int awayGoals;

    private Stats() {
        homeGoals = 0;
        awayGoals = 0;
    }

    public void homeGoal() {
        homeGoals++;
    }

    public void awayGoal() {
        awayGoals++;
    }
}
