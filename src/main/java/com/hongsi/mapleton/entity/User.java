package com.hongsi.mapleton.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "userId")
    private List<UserConHongsi> hongsiList = new ArrayList<>();
}
