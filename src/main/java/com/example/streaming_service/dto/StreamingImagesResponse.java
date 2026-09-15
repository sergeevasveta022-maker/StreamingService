package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingImagesResponse {

    private Integer total;
    private Integer totalPages;
    List<StreamingImageItem> items;

    public StreamingImagesResponse(Integer total, Integer totalPages, List<StreamingImageItem> items) {
        this.total = total;
        this.totalPages = totalPages;
        this.items = items;
    }

    public StreamingImagesResponse() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<StreamingImageItem> getItems() {
        return items;
    }

    public void setItems(List<StreamingImageItem> items) {
        this.items = items;
    }
}
