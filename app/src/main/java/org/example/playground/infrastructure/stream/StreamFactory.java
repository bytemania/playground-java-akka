package org.example.playground.infrastructure.stream;

import akka.Done;
import akka.NotUsed;
import akka.actor.typed.ActorSystem;
import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.Entity;
import akka.cluster.sharding.typed.javadsl.EntityTypeKey;
import akka.kafka.ConsumerSettings;
import akka.kafka.cluster.sharding.KafkaClusterSharding;
import akka.stream.ClosedShape;
import akka.stream.Outlet;
import akka.stream.javadsl.GraphDSL;
import akka.stream.javadsl.RunnableGraph;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import lombok.NonNull;
import lombok.Value;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.playground.infrastructure.actor.Command;
import org.example.playground.infrastructure.configuration.InboundConfig;

import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Value(staticConstructor = "of")
public class StreamFactory {

    @NonNull
    ActorSystem<?> system;

    @NonNull
    InboundConfig inboundConfig;

    public RunnableGraph<CompletionStage<Done>> of(Source<Integer, NotUsed> source, Sink<Object, CompletionStage<Done>> sink) {
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

    private void source() {

        EntityTypeKey<Command> typeKey = EntityTypeKey.create(Command.class, inboundConfig.getGroup());

        ConsumerSettings<String, Long> extractorSettings =
                ConsumerSettings.create(system, new StringDeserializer(), new LongDeserializer())
                        .withBootstrapServers(inboundConfig.getBootStrapServers())
                        .withGroupId(inboundConfig.getGroup())
                        .withProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
                        .withStopTimeout(inboundConfig.getStopTimeout());

        CompletionStage<KafkaClusterSharding.KafkaShardingNoEnvelopeExtractor<Command>> messageExtractor =
                KafkaClusterSharding.get(system)
                        .messageExtractorNoEnvelope(inboundConfig.getTopic(),
                                inboundConfig.getMessageExtractorTimeout(),
                                Command::getId,
                                extractorSettings);

        messageExtractor.thenAccept(
                extractor -> ClusterSharding.get(system).init(Entity.of(typeKey, ctx -> useractor))
        );

    }


    public Source<Integer, NotUsed> source(int n) {
        return Source.from(IntStream.range(0, n).boxed().collect(Collectors.toList()));
    }

    public static Sink<Object, CompletionStage<Done>> sink() {
        return Sink.foreach(it -> System.out.println("Done with " + it));
    }

}
