package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingSeason {

    private Integer number;
    List<StreamingEpisode> episodes;

    public StreamingSeason(Integer number, List<StreamingEpisode> episodes) {
        this.number = number;
        this.episodes = episodes;
    }

    public StreamingSeason() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<StreamingEpisode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<StreamingEpisode> episodes) {
        this.episodes = episodes;
    }
}
