package com.example.telia.Dao;

import com.example.telia.model.Horse;
import com.example.telia.model.Participant;
import com.example.telia.repository.ParticipantRespository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ParticipantDao {

    private final ParticipantRespository participantRespository;
    private final HorseDao horseDao;

    public ParticipantDao(ParticipantRespository participantRespository, HorseDao horseDao) {
        this.participantRespository = participantRespository;
        this.horseDao = horseDao;
    }

    public List<Participant> getAllParticipants() {
        return (List<Participant>) participantRespository.findAll();
    }

    public Participant addNewParticipant(Participant participant) throws Exception {
        if (!isHorseParticipating(participant)) {
            participantRespository.save(participant);
            return participant;
        }
        throw new Exception("Horse already participating");
    }

    public List<Horse> getParticipatingHorses(int race_id) {
        return getAllParticipants().stream()
                .filter(participant ->
                        participant.getRaceid().getRace_id() == race_id)
                .map(Participant::getHorseid)
                .collect(Collectors.toList());
    }

    public List<Participant> getParticipatingParticipants(int race_id) {
        return getAllParticipants().stream()
                .filter(participant ->
                        participant.getRaceid().getRace_id() == race_id)
                .collect(Collectors.toList());
    }

    public boolean isHorseParticipating(Participant participant) {
        return getAllParticipants().stream().anyMatch(p -> p.getRaceid().getRace_id() == participant.getRaceid().getRace_id() &&
                p.getHorseid().getHorse_id() == participant.getHorseid().getHorse_id());
    }

    public void deleteParticipant(Participant participant) {
        participantRespository.delete(participant);
    }

    public List<String> getParticipantColors() {
        return getAllParticipants().stream()
                .filter(participant -> participant.getRaceid().getWinner() != null &&
                        participant.getRaceid().getWinner().getHorse_id() == participant.getHorseid().getHorse_id())
                .sorted(Comparator.comparing(p -> p.getRaceid().getDate()))
                .map(Participant::getColor)
                .collect(Collectors.toList());
    }

    public List<Horse> getHorsesNotInRace(int user_id, int race_id) {
        List<Horse> userHorses = horseDao.getUserHorses(user_id);
        getAllParticipants().stream()
                .filter(participant -> participant.getRaceid().getRace_id() == race_id &&
                        userHorses.contains(participant.getHorseid()))
                .forEach(participant -> userHorses.remove(participant.getHorseid()));
        return userHorses;
    }

    public Participant getParticipantById(int participant_id) {
        return getAllParticipants().stream()
                .filter(participant -> participant.getParticipant_id() == participant_id)
                .collect(Collectors.toList()).get(0);
    }

}
