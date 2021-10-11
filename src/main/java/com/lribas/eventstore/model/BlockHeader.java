package com.lribas.eventstore.model;

import java.util.Date;

import org.bson.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockHeader {
    String parent_hash;
    String state_root_hash;
    String body_hash;
    Boolean random_bit;
    String accumulated_seed;
    Document era_end;
    Date timestamp;
    Long era_id;
    Long height;
    String protocol_version;
}
