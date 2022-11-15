package com.hongsi.mapleton.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Hongsi {
    @Id @GeneratedValue
    @Column(name = "hongsi_id")
    private Long id;

    private String title;
    private Long currentParticipant;
    private Long maxParticipant;
    private String content;
    private String startDate;
    private String endDate;
    private String status;
    private String writer;

    @OneToMany(mappedBy = "hongsiId")
    private List<UserConHongsi> boardList = new ArrayList<>();
}
