package com.dauren.TestTask.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;

}
