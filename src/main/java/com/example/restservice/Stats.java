package com.example.restservice;

import java.util.Map;

public class Stats {
    private final boolean success;
    private final int start;
    private final int limit;
    private final int size;
    private final Map<String, Object> payload;

    public Stats(
        final int start, 
        final int limit, 
        final int size, 
        final Map<String, Object> payload) {
            this.success = true;
            this.start = start;
            this.limit = limit;
            this.size = size;
            this.payload = payload;
        }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public int getSize() {
        return size;
    }
}