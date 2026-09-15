package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingReviewItem {

    private Integer kinopoiskId;
    private String type;
    private String date;
    private String author;
    private String title;
    private String description;

    public StreamingReviewItem(String type, Integer kinopoiskId, String date, String author, String title, String description) {
        this.type = type;
        this.kinopoiskId = kinopoiskId;
        this.date = date;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public StreamingReviewItem() {
    }

    public Integer getKinopoiskId() {
        return kinopoiskId;
    }

    public void setKinopoiskId(Integer kinopoiskId) {
        this.kinopoiskId = kinopoiskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
