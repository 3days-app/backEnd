package com.hongsi.mapleton.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class UserConHongsi {

    @Id @GeneratedValue
    @Column(name = "userConHongsi_id")
    private Long id;
    private Long dup = 0l;

    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Hongsi hongsiId;

    public UserConHongsi(User user, Hongsi hongsi) {
        this.userId = user;
        userId.getHongsiList().add(this);
        this.hongsiId = hongsi;
        hongsiId.getUserList().add(this);
    }
}
