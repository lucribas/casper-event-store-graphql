package com.lribas.eventstore.services;

import java.time.LocalTime;

import javax.annotation.PostConstruct;

import com.lribas.eventstore.CasperJson;
import com.lribas.eventstore.model.BlockAdded;
import com.lribas.eventstore.repository.BlockRepository;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
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


    public void save(String content) {

        JSONObject json = new JSONObject(content);

        if ( json.has("BlockAdded") ) {
            content = json.getJSONObject("BlockAdded").getJSONObject("block").toString();
            // logger.info("\t\t--->" + content);
            BlockAdded block = CasperJson.fromJson(content, BlockAdded.class);
            logger.info("\t\tDecoded as "+block);
            blockRepository.save(block);
        }
    }

    @PostConstruct
    private void init() {
        consumeFlux();
        logger.info("EventConsumer Running!!");
    }

}
