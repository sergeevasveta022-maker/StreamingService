package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingPremieresResponse {

    private Integer total;
    List<StreamingPremiereItem> items;

    public StreamingPremieresResponse(Integer total, List<StreamingPremiereItem> items) {
        this.total = total;
        this.items = items;
    }

    public StreamingPremieresResponse() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<StreamingPremiereItem> getItems() {
        return items;
    }

    public void setItems(List<StreamingPremiereItem> items) {
        this.items = items;
    }
}
