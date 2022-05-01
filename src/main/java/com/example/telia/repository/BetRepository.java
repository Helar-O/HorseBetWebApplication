package com.example.telia.repository;

import com.example.telia.model.Bet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends CrudRepository<Bet, Long> {
}
