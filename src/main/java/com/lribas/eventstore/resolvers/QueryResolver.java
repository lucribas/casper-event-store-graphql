package com.lribas.eventstore.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lribas.eventstore.error.BlockNotFoundException;
import com.lribas.eventstore.model.Block;
import com.lribas.eventstore.repository.BlockRepository;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    private final BlockRepository blockRepository;

    public Block block(String hashId) {
        return blockRepository.findById(hashId)
                .orElseThrow(() -> new BlockNotFoundException("Block not found", hashId));
    }

    public Iterable<Block> allBlocks() {
        return blockRepository.findAll();
    }

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
								 + Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}

}
