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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hongsi_id")
    private Hongsi hongsi;

    public UserConHongsi(User user, Hongsi hongsi) {
        this.user = user;
        user.getHongsiList().add(this);
        this.hongsi = hongsi;
        hongsi.getUserList().add(this);
    }
}
