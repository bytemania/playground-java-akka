package org.example.playground.domain.incident;

import lombok.Value;

@Value(staticConstructor = "of")
public class Clock {
    Period period;
    int minutes;
    int seconds;
}
