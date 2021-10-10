package com.lribas.eventstore.model;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("block")
public class Block {
	String hashId;
    List<String> header;
    List<String> body;
    List<String> proofs;
    @Version
    private Long version;
    @CreatedDate
    private DateTime createdAt;
    @LastModifiedDate
    private DateTime lastModified;
}
