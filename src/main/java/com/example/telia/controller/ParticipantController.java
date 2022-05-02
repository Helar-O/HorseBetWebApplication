package com.example.telia.controller;

import com.example.telia.dao.ParticipantDao;
import com.example.telia.model.Horse;
import com.example.telia.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ParticipantController {

    private final ParticipantDao participantDao;

    @Autowired
    public ParticipantController(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    @PostMapping("/participant/new")
    public Participant addNewParticipant(@RequestBody Participant participant) throws Exception {
        return participantDao.addNewParticipant(participant);
    }

    @GetMapping("/participant/all")
    public List<Participant> getAllParticipants() {
        return participantDao.getAllParticipants();
    }

    @GetMapping("/participant/color")
    public List<String> getHistoryColors() { return participantDao.getParticipantColors(); }

    @GetMapping("/participant/race/{race_id}")
    public List<Participant> getRaceParticipants(@PathVariable int race_id) {
        return participantDao.getParticipatingParticipants(race_id);
    }

    @GetMapping("/participant/available/{user_id}/{race_id}")
    public List<Horse> getAvailableUserHorses(@PathVariable int user_id, int race_id) {
        return participantDao.getHorsesNotInRace(user_id, race_id);
    }

}