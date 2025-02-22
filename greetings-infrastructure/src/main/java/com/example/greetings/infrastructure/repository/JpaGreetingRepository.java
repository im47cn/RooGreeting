package com.example.greetings.infrastructure.repository;

import com.example.greetings.domain.model.Greeting;
import com.example.greetings.domain.repository.GreetingRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaGreetingRepository extends JpaRepository<Greeting, Long>, GreetingRepository {
    @Override
    @Query("SELECT g.greetingDate, g.username, COUNT(g) as count " +
           "FROM Greeting g " +
           "GROUP BY g.greetingDate, g.username " +
           "ORDER BY g.greetingDate DESC, COUNT(g) DESC")
    List<Object[]> findDailyGreetingCounts();
}