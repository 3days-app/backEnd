package com.hongsi.mapleton.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Board {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hongsi hongsi;
}
