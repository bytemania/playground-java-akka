package org.example.playground.app;

import akka.Done;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.Terminated;
import akka.actor.typed.javadsl.Behaviors;
import akka.stream.javadsl.RunnableGraph;
import org.example.playground.infrastructure.stream.StreamFactory;

import java.util.concurrent.CompletionStage;

public class Main {

    private static Behavior<Void> create() {
        return Behaviors.setup(context ->
                Behaviors.receive(Void.class)
                        .onSignal(Terminated.class, signal -> Behaviors.stopped())
                        .build()
        );
    }

    public static void main(String[] args) {
        ActorSystem<Void> system = ActorSystem.create(Main.create(), "GameStateSystem");

        RunnableGraph<CompletionStage<Done>> stream = StreamFactory.of(StreamFactory.source(10), StreamFactory.sink());

        stream.run(system);
    }

}
