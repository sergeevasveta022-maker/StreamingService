package com.example.streaming_service.dto;

import java.util.List;

public class StreamingSearchResponse {

    Integer total;

    Integer totalPages;

    List<StreamingFilmItem> items;

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

    public List<StreamingFilmItem> getItems() {
        return items;
    }

    public void setItems(List<StreamingFilmItem> items) {
        this.items = items;
    }
}
