package org.example.playground.model.event;

import lombok.Value;
import org.example.playground.model.domain.Match;

@Value(staticConstructor = "of")
public class MatchUpdated {
    Match match;
}
