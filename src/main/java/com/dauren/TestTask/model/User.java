package com.dauren.TestTask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Validated
@Entity
@Table(name = "t_users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    private String name;

    private String email;

    private String password;

    private LocalDateTime creationDate = LocalDateTime.now();

}
