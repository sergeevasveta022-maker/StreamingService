package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StreamingGenre {

    @JsonProperty("genre")
    private String genre;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
