package org.example.playground.model.domain;


import lombok.Data;

@Data
public class Stats {

    private int homeGoals;
    private int awayGoals;

    private Stats() {
        homeGoals = 0;
        awayGoals = 0;
    }

    public static Stats create() {
        return new Stats();
    }

}
