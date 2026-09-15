package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StreamingImageItem {

    private String imageUrl;
    private String previewUrl;

    public StreamingImageItem(String imageUrl, String previewUrl) {
        this.imageUrl = imageUrl;
        this.previewUrl = previewUrl;
    }

    public StreamingImageItem() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }
}
