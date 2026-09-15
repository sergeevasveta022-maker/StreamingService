package com.example.streaming_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StreamingStaffItem {

    private Integer staffId;
    private String nameRu;
    private String nameEn;
    private String description;
    @JsonProperty("posterUrl")
    private String actorPhoto;
    private String professionText;

    public StreamingStaffItem(Integer staffId, String actorNameRu, String actorNameEn, String description, String actorPhoto, String professionText) {
        this.staffId = staffId;
        this.nameRu = actorNameRu;
        this.nameEn = actorNameEn;
        this.description = description;
        this.actorPhoto = actorPhoto;
        this.professionText = professionText;
    }

    public StreamingStaffItem() {
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getActorNameRu() {
        return nameRu;
    }

    public void setActorNameRu(String actorNameRu) {
        this.nameRu = actorNameRu;
    }

    public String getActorNameEn() {
        return nameEn;
    }

    public void setActorNameEn(String actorNameEn) {
        this.nameEn = actorNameEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActorPhoto() {
        return actorPhoto;
    }

    public void setActorPhoto(String actorPhoto) {
        this.actorPhoto = actorPhoto;
    }

    public String getProfessionText() {
        return professionText;
    }

    public void setProfessionText(String professionText) {
        this.professionText = professionText;
    }
}
