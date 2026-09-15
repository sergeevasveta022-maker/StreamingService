package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingReviewsResponse {

    private Integer total;
    private Integer totalPages;
    List<StreamingReviewItem> items;

    public StreamingReviewsResponse(Integer total, Integer totalPages, List<StreamingReviewItem> items) {
        this.total = total;
        this.totalPages = totalPages;
        this.items = items;
    }

    public StreamingReviewsResponse() {
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

    public List<StreamingReviewItem> getItems() {
        return items;
    }

    public void setItems(List<StreamingReviewItem> items) {
        this.items = items;
    }
}
