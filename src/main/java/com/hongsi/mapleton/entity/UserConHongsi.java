package com.hongsi.mapleton.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserConHongsi {

    @Id @GeneratedValue
    @Column(name = "userConHongsi_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hongsi hongsiId;
}
