package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StreamingFilmItem {

    @JsonProperty("kinopoiskId")
    Integer streamingId;

    String nameRu;

    Integer year;

    @JsonProperty("ratingKinopoisk")
    Double ratingStreaming;

    String type;

    List<StreamingGenre> genres;

    List<StreamingCountry> countries;

    public Integer getStreamingId() {
        return streamingId;
    }

    public void setStreamingId(Integer streamingId) {
        this.streamingId = streamingId;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRatingStreaming() {
        return ratingStreaming;
    }

    public void setRatingStreaming(Double ratingStreaming) {
        this.ratingStreaming = ratingStreaming;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<StreamingGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<StreamingGenre> genres) {
        this.genres = genres;
    }

    public List<StreamingCountry> getCountries() {
        return countries;
    }

    public void setCountries(List<StreamingCountry> countries) {
        this.countries = countries;
    }
}
