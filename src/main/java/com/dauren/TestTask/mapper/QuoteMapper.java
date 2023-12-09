package com.dauren.TestTask.mapper;

import com.dauren.TestTask.dto.QuoteDto;
import com.dauren.TestTask.model.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuoteMapper {

    QuoteDto toDto(Quote quote);

    Quote toModel(QuoteDto quoteDto);

    List<QuoteDto> toDtoList(List<Quote> quotes);
}
