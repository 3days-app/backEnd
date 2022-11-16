package com.hongsi.mapleton.entity;

import com.hongsi.mapleton.dto.RequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Hongsi {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<UserConHongsi> userList = new ArrayList<>();

    @OneToMany(mappedBy = "hongsi")
    private List<Board> boardList = new ArrayList<>();

    public Hongsi (RequestDto requestDto){
        this.title = requestDto.getTitle();
        this.currentParticipant = requestDto.getCurrentParticipant();
        this.maxParticipant = requestDto.getMaxParticipant();
        this.content = requestDto.getContent();
        this.startDate = requestDto.getStartDate();
        this.endDate = requestDto.getEndDate();
        this.writer = requestDto.getWriter();
    }
}
