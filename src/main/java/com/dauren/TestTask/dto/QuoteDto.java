package com.dauren.TestTask.dto;

import com.dauren.TestTask.model.Vote;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class QuoteDto {
    private Long id;
    private String content;
    private LocalDateTime creationDate = LocalDateTime.now();
    private UserDto user;
}
