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

    private String content;
    private String image;
    private String deleteImage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hongsi hongsi;

    public Board(Hongsi hongsis){
        this.hongsi = hongsis;
        hongsis.getBoardList().add(this);
    }
}
