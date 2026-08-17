package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StreamingCountry {

    @JsonProperty("country")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
