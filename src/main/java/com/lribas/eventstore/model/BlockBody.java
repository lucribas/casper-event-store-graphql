package com.lribas.eventstore.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockBody {
    String proposer;
    List<String> deploy_hashes;
    List<String> transfer_hashes;
}
