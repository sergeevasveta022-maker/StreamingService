package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingSeasonsResponse {

    private Integer total;
    List<StreamingSeason> items;

    public StreamingSeasonsResponse(Integer total, List<StreamingSeason> items) {
        this.total = total;
        this.items = items;
    }

    public StreamingSeasonsResponse() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<StreamingSeason> getItems() {
        return items;
    }

    public void setItems(List<StreamingSeason> items) {
        this.items = items;
    }
}
