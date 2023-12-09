package com.dauren.TestTask.api;

import com.dauren.TestTask.model.Quote;
import com.dauren.TestTask.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Valid
@RestController
@RequestMapping("/api/v1/vote")
@RequiredArgsConstructor
public class VoteRestController {

    private final VoteService voteService;
        @PostMapping("/upvote/{quoteId}/{userId}")
        public ResponseEntity<String> upVote(
                @PathVariable(name = "quoteId") @Valid Long quoteId,
                @PathVariable(name = "userId") @Valid  Long userId
        ) {
            return voteService.upVote(quoteId, userId);
        }



    @PostMapping("/downvote/{quoteId}/{userId}")
    public ResponseEntity<String> downVote(
        @NotNull(message = "Идентификатор цитаты не может быть пустым") @PathVariable(name = "quoteId") final Long quoteId,
        @NotNull(message = "Идентификатор цитаты не может быть пустым") @PathVariable(name = "userId") final Long userId
    ) {
        return voteService.downVote(quoteId, userId);
    }


}
