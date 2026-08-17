package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingFilmDetails {

    @JsonProperty("kinopoiskId")
    private Long streamingId;

    private String description;

    public Long getStreamingId() {
        return streamingId;
    }

    public void setStreamingId(Long streamingId) {
        this.streamingId = streamingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
