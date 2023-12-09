package com.dauren.TestTask.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

//@NamedQueries(
//        {
//                @NamedQuery(
//                        name="AB",
//                        query =  "SELECT q.* " +
//                        "FROM t_quotes q " +
//                        "JOIN t_votes v ON q.id = v.quote_id " +
//                        "WHERE v.vote_type = 'UPVOTE' " +
//                        "GROUP BY q.id " +
//                        "ORDER BY COUNT(v.id) DESC LIMIT 10"
//                )
//        }
//)
@Validated
@Entity
@Table(name = "t_quotes")
@Getter
@Setter
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    private String content;

    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    private List<Vote> votes;

}
