package org.example.playground.infrastructure.stream;

import akka.Done;
import akka.NotUsed;
import akka.stream.ClosedShape;
import akka.stream.Outlet;
import akka.stream.javadsl.GraphDSL;
import akka.stream.javadsl.RunnableGraph;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StreamFactory {

    public static RunnableGraph<CompletionStage<Done>> of(Source<Integer, NotUsed> source, Sink<Object, CompletionStage<Done>> sink) {
        return RunnableGraph.fromGraph(
                GraphDSL.create(
                        sink,
                        (builder, out) -> {
                            final Outlet<Integer> outletSource = builder.add(source).out();
                            builder.from(outletSource).to(out);
                            return ClosedShape.getInstance();
                        }
                )
        );
    }

    public static Source<Integer, NotUsed> source(int n) {
        return Source.from(IntStream.range(0, n).boxed().collect(Collectors.toList()));
    }

    public static Sink<Object, CompletionStage<Done>> sink() {
        return Sink.foreach(it -> System.out.println("Done with " + it));
    }

}
