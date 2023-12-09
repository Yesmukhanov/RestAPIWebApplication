package com.dauren.TestTask.api;

import com.dauren.TestTask.dto.QuoteDto;
import com.dauren.TestTask.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quotes")
@RequiredArgsConstructor
public class QuoteRestController {

    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteDto> createQuote(@RequestBody final QuoteDto quote) {
        return quoteService.createQuote(quote);
    }


    @GetMapping("/{quoteId}")
    public ResponseEntity<QuoteDto> getQuote(@PathVariable(name = "quoteId") final Long quoteId){
        return quoteService.getQuote(quoteId);
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteDto> getRandomQuote(){
        return quoteService.getRandomQuote();
    }

    @PostMapping("/update")
    public ResponseEntity<QuoteDto> updateQuote(@RequestBody final QuoteDto quote){
        return quoteService.updateQuote(quote);
    }

    @PostMapping("/delete/{quoteId}")
    public ResponseEntity<String> deleteQuote(@PathVariable(name = "quoteId") final Long quoteId) {
        return quoteService.deleteQuote(quoteId);
    }

    @GetMapping("/top")
    public ResponseEntity<List<QuoteDto>> getTopQuotes() {
        return quoteService.getTopQuotes();
    }

    @GetMapping("/worse")
    public ResponseEntity<List<QuoteDto>> getWorseQuotes() {
        return quoteService.getWorseQuotes();
    }

    @GetMapping("/info/{quoteId}")
    public Object[] getQuoteDetail(@PathVariable(name = "quoteId") final Long id){
        return quoteService.getQuoteDetail(id);
    }


}
