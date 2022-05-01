package com.example.telia.repository;

import com.example.telia.model.Participant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRespository extends CrudRepository<Participant, Long> { }
