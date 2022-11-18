package com.hongsi.mapleton.dto;

import com.hongsi.mapleton.entity.Hongsi;
import lombok.Data;

@Data
public class HongsiListDto {
    private Long hongsi_id;
    private String title;
    private String startDate;
    private String endDate;
    private Long currentParticipant;
    private Long maxParticipant;
    private String image;

    public HongsiListDto(Hongsi hongsi){
        this.hongsi_id = hongsi.getId();
        this.title = hongsi.getTitle();
        this.startDate = hongsi.getStartDate();
        this.endDate = hongsi.getEndDate();
        this.currentParticipant = hongsi.getCurrentParticipant();
        this.maxParticipant = hongsi.getMaxParticipant();
        this.image = hongsi.getImage();
    }
}
