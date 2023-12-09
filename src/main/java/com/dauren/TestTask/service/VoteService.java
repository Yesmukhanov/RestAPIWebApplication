package com.dauren.TestTask.service;

import com.dauren.TestTask.model.Quote;
import com.dauren.TestTask.model.User;
import com.dauren.TestTask.model.Vote;
import com.dauren.TestTask.model.VoteType;
import com.dauren.TestTask.repository.QuoteRepository;
import com.dauren.TestTask.repository.UserRepository;
import com.dauren.TestTask.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;

    public ResponseEntity<String> upVote(final Long quoteId, final Long userId) {

        if (checkUserAndQuote(userId, quoteId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER OR QUOTE NOT FOUND");
        }

        final User user = userRepository.findById(userId).orElse(null);
        final Quote quote = quoteRepository.findById(quoteId).orElse(null);


        final Vote vote = Vote.builder()
                .user(user)
                .quote(quote)
                .voteType(VoteType.UPVOTE)
                .build();

        voteRepository.save(vote);

        return ResponseEntity.status(HttpStatus.OK).body("YOU ARE VOTED");

    }

    public ResponseEntity<String> downVote(Long quoteId, Long userId) {

        if (checkUserAndQuote(userId, quoteId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER OR QUOTE NOT FOUND");
        }

        final User user = userRepository.findById(userId).orElse(null);
        final Quote quote = quoteRepository.findById(quoteId).orElse(null);


        final Vote vote = Vote.builder()
                .user(user)
                .quote(quote)
                .voteType(VoteType.DOWNVOTE)
                .build();

        voteRepository.save(vote);

        return ResponseEntity.status(HttpStatus.OK).body("YOU ARE VOTED");
    }


    public boolean checkUserAndQuote(final Long userId, final Long quoteId) {
        User user = userRepository.findById(userId).orElse(null);
        Quote quote = quoteRepository.findById(quoteId).orElse(null);

        return Objects.isNull(user) || Objects.isNull(quote);
    }

}
