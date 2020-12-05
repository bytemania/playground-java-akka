package org.example.playground.event;

import lombok.Value;
import org.example.playground.domain.Match;

@Value(staticConstructor = "of")
public class MatchUpdated {
    Match match;
}
