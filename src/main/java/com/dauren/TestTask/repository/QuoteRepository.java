package com.dauren.TestTask.repository;

import com.dauren.TestTask.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(value = "SELECT q.*, (v.upvotes - v.downvotes) AS difference " +
            "FROM t_quotes q " +
            "JOIN (SELECT quote_id, " +
            "SUM(CASE WHEN vote_type = 'UPVOTE' THEN 1 ELSE 0 END) AS upvotes, " +
            "SUM(CASE WHEN vote_type = 'DOWNVOTE' THEN 1 ELSE 0 END) AS downvotes " +
            "FROM t_votes GROUP BY quote_id) v " +
            "ON q.id = v.quote_id " +
            "ORDER BY difference DESC LIMIT 10", nativeQuery = true)
    List<Quote> getTopByDifference();

    @Query(value = "SELECT q.*, (v.upvotes - v.downvotes) AS difference " +
            "FROM t_quotes q " +
            "JOIN (SELECT quote_id, " +
            "SUM(CASE WHEN vote_type = 'UPVOTE' THEN 1 ELSE 0 END) AS upvotes, " +
            "SUM(CASE WHEN vote_type = 'DOWNVOTE' THEN 1 ELSE 0 END) AS downvotes " +
            "FROM t_votes GROUP BY quote_id) v " +
            "ON q.id = v.quote_id " +
            "ORDER BY difference ASC LIMIT 10", nativeQuery = true)
    List<Quote> getTopWorstQuotes();


    @Query(value = "SELECT q.*, (v.upvotes - v.downvotes) AS difference " +
            "FROM t_quotes q " +
            "LEFT JOIN (SELECT quote_id, " +
            "SUM(CASE WHEN vote_type = 'UPVOTE' THEN 1 ELSE 0 END) AS upvotes, " +
            "SUM(CASE WHEN vote_type = 'DOWNVOTE' THEN 1 ELSE 0 END) AS downvotes " +
            "FROM t_votes GROUP BY quote_id) v " +
            "ON q.id = v.quote_id " +
            "WHERE q.id = :quoteId", nativeQuery = true)
    Object[] getQuoteDetailsWithDifference(@Param("quoteId") Long quoteId);






}