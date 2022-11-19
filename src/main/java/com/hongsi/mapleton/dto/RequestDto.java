package com.hongsi.mapleton.dto;

import lombok.Data;

@Data
public class RequestDto {
    private Long user_id;
    private Long hongsi_id;
    private String title;
    private String startDate;
    private String endDate;
    private Long currentParticipant;
    private Long maxParticipant;
    private String content;
    private String writer;
    private String category;
}
