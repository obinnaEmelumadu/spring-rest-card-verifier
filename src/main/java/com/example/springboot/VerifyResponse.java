package com.example.springboot;

public class VerifyResponse {
    private final boolean success;
    private final Payload payload;

    public VerifyResponse(boolean success, Payload payload) {
        this.success = success;
        this.payload = payload;
    }

    public Payload getPayload() {
        return payload;
    }

    public boolean isSuccess() {
        return success;
    }
}