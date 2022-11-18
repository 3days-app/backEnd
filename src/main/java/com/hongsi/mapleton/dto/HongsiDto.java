package com.hongsi.mapleton.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class HongsiDto {
    private Long id;
    private String title;
    private String startDate;
    private String endDate;
}
