package com.lribas.eventstore.error;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class BlockNotFoundException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();

    public BlockNotFoundException(String message, String invalidBlockid) {
        super(message);
        extensions.put("invalidBlockid", invalidBlockid);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }
}
