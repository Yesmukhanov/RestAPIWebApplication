package com.dauren.TestTask.dto;

import com.dauren.TestTask.model.Quote;
import com.dauren.TestTask.model.VoteType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteDto {
    private Long id;
    private UserDto user;
    private Quote quote;
    private VoteType voteType;
}
