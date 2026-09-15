package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingSimilarFilmItem {

    @JsonProperty("filmId")
    private Integer streamingId;
    private String nameRu;
    private String nameEn;
    private String posterUrl;

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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
