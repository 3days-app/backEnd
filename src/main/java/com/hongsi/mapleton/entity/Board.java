package com.hongsi.mapleton.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "board")
@NoArgsConstructor
public class Board {
    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hongsi hongsi;
}
