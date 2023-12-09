package com.dauren.TestTask.service;

import com.dauren.TestTask.dto.QuoteDto;
import com.dauren.TestTask.mapper.QuoteMapper;
import com.dauren.TestTask.model.Quote;
import com.dauren.TestTask.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteMapper quoteMapper;
    private final QuoteRepository quoteRepository;

    public ResponseEntity<QuoteDto> createQuote(final QuoteDto quote) {
        Quote newQuote = quoteMapper.toModel(quote); // transfer to model
        QuoteDto quoteDto = quoteMapper.toDto(quoteRepository.save(newQuote)); // save to database

        return ResponseEntity.status(HttpStatus.CREATED).body(quoteDto);
    }

    public ResponseEntity<QuoteDto> getQuote(final Long quoteId) {
        QuoteDto quote = quoteMapper.toDto(quoteRepository.findById(quoteId).orElse(null));
        if (Objects.isNull(quote)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(quote);
    }

    public ResponseEntity<QuoteDto> getRandomQuote() {
        List<Quote> quotes = quoteRepository.findAll();
        System.out.println(quotes);
        if (quotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        int randomIndex = new Random().nextInt(quotes.size());

        QuoteDto quote = quoteMapper.toDto(quotes.get(randomIndex));

        return ResponseEntity.status(HttpStatus.OK).body(quote);

    }

    public ResponseEntity<QuoteDto> updateQuote(final QuoteDto quote) {
        System.out.println(quote.getId());
        Quote checkQuote = quoteRepository.findById(quote.getId()).orElse(null);

        if (Objects.isNull(checkQuote)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Quote updateQuote = quoteRepository.save(quoteMapper.toModel(quote));

        return ResponseEntity.status(HttpStatus.OK).body(quoteMapper.toDto(updateQuote));
    }

    public ResponseEntity<String> deleteQuote(final Long quoteId) {
        quoteRepository.deleteById(quoteId);
        return ResponseEntity.status(HttpStatus.OK).body("QUOTE DELETED");
    }

    public ResponseEntity<List<QuoteDto>> getTopQuotes() {
        List<QuoteDto> quotes = quoteMapper.toDtoList(quoteRepository.getTopByDifference());
        if (quotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(quotes);
    }


    public ResponseEntity<List<QuoteDto>> getWorseQuotes() {
        List<QuoteDto> quotes = quoteMapper.toDtoList(quoteRepository.getTopWorstQuotes());
        if (quotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(quotes);
    }

    public Object[] getQuoteDetail(final Long id) {
        return  quoteRepository.getQuoteDetailsWithDifference(id);
    }
}
