package com.hongsi.mapleton.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_con_hongsi")
@NoArgsConstructor
public class UserConHongsi {

    @Id @GeneratedValue
    @Column(name = "user_con_hongsi_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users usersId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hongsi_id")
    private Hongsi hongsi;

    public UserConHongsi(Users users, Hongsi hongsis) {
        this.usersId = users;
        usersId.getHongsiList().add(this);
        this.hongsi = hongsis;
        hongsi.getUserList().add(this);
    }
}
