package com.hongsi.mapleton.dto;

import com.hongsi.mapleton.entity.Hongsi;
import lombok.Data;

@Data
public class HongsiDetailDto {
    private Long hongsi_id;
    private String title;
    private String startDate;
    private String endDate;
    private Long currentParticipant;
    private Long maxParticipant;
    private String content;
    private String writer;
    private String image;

    public HongsiDetailDto(Hongsi hongsi){
        this.hongsi_id = hongsi.getId();
        this.title = hongsi.getTitle();
        this.startDate = hongsi.getStartDate();
        this.endDate = hongsi.getEndDate();
        this.currentParticipant = hongsi.getCurrentParticipant();
        this.maxParticipant = hongsi.getMaxParticipant();
        this.content = hongsi.getContent();
        this.writer = hongsi.getWriter();
        this.image = hongsi.getImage();
    }
}
