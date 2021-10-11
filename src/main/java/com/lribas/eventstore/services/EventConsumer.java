package com.lribas.eventstore.services;

import java.time.LocalTime;

import javax.annotation.PostConstruct;

import com.lribas.eventstore.CasperJson;
import com.lribas.eventstore.model.BlockAdded;
import com.lribas.eventstore.repository.BlockRepository;

import org.json.JSONObject;
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
		// BlockAdded block = BlockAdded.builder().hash(hash).build();
		JSONObject json = new JSONObject(content);

		if ( json.has("BlockAdded") ) {
			// content = "{\"hash\":\"13e10e05cefb37e45381bc5d5bddcdf8de67e6dc5bfcc58b71150a1410210f18\",\"header\":{\"parent_hash\":\"2ad90349c6aaabf0625b7ffdadc8ac126609129dfc647d8c8d5d96cad6fed14f\",\"state_root_hash\":\"445158bd55e8d5e7a06373bc3113737f5a01a91eea5d27a8ba6f350b59379c39\",\"body_hash\":\"935473d9e97601d647b146a2a76f3f7a6ac07b998b0b53a83491d826efd9c65a\",\"random_bit\":true,\"accumulated_seed\":\"9893cc273b183fa67320e67b552e280182e8258c3283a96031ca5cfff7387f99\",\"era_end\":null,\"timestamp\":\"2021-10-10T22:44:24.704Z\",\"era_id\":46,\"height\":512,\"protocol_version\":\"1.0.0\"},\"body\":{\"proposer\":\"01d21a6cd696f2438d3251fa24801f76cc3252b8c7e8cf5e5f0389dde53e552042\",\"deploy_hashes\":[],\"transfer_hashes\":[]},\"proofs\":[]}";
			content = json.getJSONObject("BlockAdded").getJSONObject("block").toString();
			System.out.println("\t\t--->" + content);

			BlockAdded block = CasperJson.fromJson(content, BlockAdded.class);
			System.out.println("\t\t"+block);

			// BlockHeader bh = BlockHeader.builder().parent_hash("23487324").build();
			// BlockBody bb = BlockBody.builder().proposer("prop_lus").build();
			// List<String> p = new ArrayList<String>();
			// p.add("abacaxi_prooff");
			// BlockAdded b = BlockAdded.builder().hash("40928374").header(bh).body(bb).proofs(p).build();
			// System.out.println("\t\t"+CasperJson.toJson(b));
			blockRepository.save(block);
		}
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
