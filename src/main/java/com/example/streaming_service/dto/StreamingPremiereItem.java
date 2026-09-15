package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingPremiereItem {

    private Integer kinopoiskId;
    private Integer year;
    private Integer duration;
    private String nameRu;
    private String nameEn;
    private String posterUrl;
    private String premiereRu;
    List<StreamingCountry> countries;
    List<StreamingGenre> genres;

    public StreamingPremiereItem(Integer kinopoiskId, Integer year, Integer duration, String nameRu, String nameEn, String posterUrl, String premiereRu, List<StreamingCountry> countries, List<StreamingGenre> genres) {
        this.kinopoiskId = kinopoiskId;
        this.year = year;
        this.duration = duration;
        this.nameRu = nameRu;
        this.nameEn = nameEn;
        this.posterUrl = posterUrl;
        this.premiereRu = premiereRu;
        this.countries = countries;
        this.genres = genres;
    }

    public StreamingPremiereItem() {
    }

    public Integer getKinopoiskId() {
        return kinopoiskId;
    }

    public void setKinopoiskId(Integer kinopoiskId) {
        this.kinopoiskId = kinopoiskId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public String getPremiereRu() {
        return premiereRu;
    }

    public void setPremiereRu(String premiereRu) {
        this.premiereRu = premiereRu;
    }

    public List<StreamingCountry> getCountries() {
        return countries;
    }

    public void setCountries(List<StreamingCountry> countries) {
        this.countries = countries;
    }

    public List<StreamingGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<StreamingGenre> genres) {
        this.genres = genres;
    }
}
