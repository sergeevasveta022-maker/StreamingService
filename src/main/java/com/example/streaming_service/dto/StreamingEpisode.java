package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingEpisode {

    private Integer seasonNumber;
    private Integer episodeNumber;
    private String nameRu;
    private String nameEn;
    private String synopsis;
    private String releaseDate;

    public StreamingEpisode(Integer seasonNumber, Integer episodeNumber, String nameRu, String nameEn, String synopsis, String releaseDate) {
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.synopsis = synopsis;
        this.releaseDate = releaseDate;
    }

    public StreamingEpisode() {
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
