package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingSimilarFilmsResponse {

    private Integer total;
    private List<StreamingSimilarFilmItem> items;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<StreamingSimilarFilmItem> getItems() {
        return items;
    }

    public void setItems(List<StreamingSimilarFilmItem> items) {
        this.items = items;
    }
}
