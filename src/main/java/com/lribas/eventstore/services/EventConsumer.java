package com.lribas.eventstore.services;

import java.time.LocalTime;

import javax.annotation.PostConstruct;

import com.lribas.eventstore.model.BlockAdded;
import com.lribas.eventstore.repository.BlockRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class EventConsumer {

	private final BlockRepository blockRepository;

	private static Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    private WebClient client = WebClient.create("http://localhost:18102");

	public void save(String content) {

		String hash = "01234";
		BlockAdded block = BlockAdded.builder().hash(hash).build();
		blockRepository.save(block);
	}

	@Async
    public void consumeSSE() {
        ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<ServerSentEvent<String>>() {
        };

        Flux<ServerSentEvent<String>> eventStream = client.get()
            .uri("/events/main")
            .retrieve()
            .bodyToFlux(type);

        eventStream.subscribe(content -> logger.info("Current time: {} - Received SSE: name[{}], id [{}], content[{}] ", LocalTime.now(), content.event(), content.id(), content.data()), error -> logger.error("Error receiving SSE: {}", error),
            () -> logger.info("Completed!!!"));
    }

    @Async
    public void consumeFlux() {
        Flux<String> stringStream = client.get()
            .uri("/events/main")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(String.class);

        stringStream.subscribe(content -> {
			logger.info("Current time: {} - Received content: {} ", LocalTime.now(), content);
			save(content);
		}, error -> logger.error("Error retrieving content: {}", error), () -> logger.info("Completed!!!"));
    }

    @Async
    public void consumeSSEFromFluxEndpoint() {
        ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<ServerSentEvent<String>>() {
        };

        Flux<ServerSentEvent<String>> eventStream = client.get()
            .uri("/events/main")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .retrieve()
            .bodyToFlux(type);

        eventStream.subscribe(content -> logger.info("Current time: {} - Received SSE: name[{}], id [{}], content[{}] ", LocalTime.now(), content.event(), content.id(), content.data()), error -> logger.error("Error receiving SSE: {}", error),
            () -> logger.info("Completed!!!"));
    }

	@PostConstruct
	private void init() {
		consumeFlux();
		logger.info("EventConsumer Running!!");
	}

}
