package com.dauren.TestTask.mapper;

import com.dauren.TestTask.dto.VoteDto;
import com.dauren.TestTask.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    VoteDto toDto(Vote vote);

    Vote toModel(VoteDto voteDto);
    List<VoteDto> toDtoList(List<Vote> votes);
}
