package com.example.springboot;

public class Payload {
    private final String scheme;
    private final String type;
    private final String bank;

    public Payload(String scheme, String type, String bank) {
        this.scheme = scheme;
        this.type = type;
        this.bank = bank;
    }

    public String getType() {
        return type;
    }

    public String getBank() {
        return bank;
    }

    public String getScheme() {
        return scheme;
    }
}