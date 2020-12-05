package org.example.playground.model.domain.incident;

import lombok.Value;

@Value(staticConstructor = "of")
public class Clock {
    Period period;
    int minutes;
    int seconds;
}
