package com.hongsi.mapleton.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
@NoArgsConstructor
public class Users {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;

    public Users(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    @OneToMany(mappedBy = "usersId")
    private List<UserConHongsi> hongsiList = new ArrayList<>();
}
