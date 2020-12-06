package org.example.playground.model.domain.incident;

import lombok.Value;

@Value
public class Clock {
    Period period;
    int minutes;
    int seconds;
}
