package org.example.playground.infrastructure.configuration;

import akka.actor.typed.ActorSystem;
import com.typesafe.config.Config;
import lombok.Getter;

import java.time.Duration;

@Getter
public class InboundConfig {

    private final String bootStrapServers;
    private final String topic;
    private final String group;
    private final Duration stopTimeout;
    private final Duration messageExtractorTimeout;

    private InboundConfig(ActorSystem<?> system, String configLocation) {
        Config config = system.settings().config().getConfig(configLocation);

        bootStrapServers = config.getString("bootstrap-servers");
        topic = config.getString("topic");
        group = config.getString("group");
        stopTimeout = config.getDuration("stop-timeout");
        messageExtractorTimeout = config.getDuration("message-extractor-timeout");
    }

    public static InboundConfig of(ActorSystem<?> system, String configLocation) {
        return new InboundConfig(system, configLocation);
    }
}
