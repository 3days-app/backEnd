package com.hongsi.mapleton.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class HongsiDto {

    @Id
    @GeneratedValue
    @Column(name = "hongsi_id")
    private Long id;

    public String title;
    public String startDate;
    public String endDate;
}
