package com.hongsi.mapleton.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDto {
    private Long user_id;
    private String email;
    private String password;
    private String nickname;
}
