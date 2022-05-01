package com.example.telia.repository;

import com.example.telia.model.Horse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends CrudRepository<Horse, Long> {
}
