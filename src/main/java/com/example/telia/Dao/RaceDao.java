package com.example.telia.Dao;

import com.example.telia.model.Horse;
import com.example.telia.model.Race;
import com.example.telia.repository.RaceRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class RaceDao {

    private final RaceRepository raceRepository;
    private final ParticipantDao participantDao;

    public RaceDao(RaceRepository raceRepository, ParticipantDao participantDao) {
        this.raceRepository = raceRepository;
        this.participantDao = participantDao;
    }

    public List<Race> getAllRaces() {
        return (List<Race>) raceRepository.findAll();
    }

    public Race addRace(Race race) throws Exception {
        if (race.getPlace().isEmpty() || race.getDate() == null || race.getTime() == null) {
            throw new Exception("Invalid Race Details");
        }
        if (race.getDate().isBefore(LocalDate.now())
                || race.getDate().equals(LocalDate.now()) && race.getTime().isBefore(LocalTime.now())) {
            throw new Exception("Invalid Race DateTime");
        }
        raceRepository.save(race);
        return race;
    }

    public Race getRaceById(int race_id) {
        return getAllRaces().stream()
                .filter(race -> race.getRace_id() == race_id)
                .collect(Collectors.toList()).get(0);
    }

    public List<Race> getOngoingRaces() {
        return getAllRaces().stream()
                .filter(race -> race.getDate().isAfter(LocalDate.now()) ||
                        race.getDate().equals(LocalDate.now()) &&
                                race.getTime().isAfter(LocalTime.now()))
                .sorted(Comparator.comparing(Race::getDate))
                .collect(Collectors.toList());
    }

    public List<Race> getPreviousRaces() {
        return getAllRaces().stream()
                .filter(race -> (race.getDate().isBefore(LocalDate.now()) ||
                        race.getDate().equals(LocalDate.now()) &&
                                race.getTime().isBefore(LocalTime.now()))
                        && hasWinner(race)) // Declare a winner if null
                .sorted(Comparator.comparing(Race::getDate))
                .collect(Collectors.toList());
    }

    public boolean hasWinner(Race race) {
        if (race.getWinner() == null) declareWinner(race);
        return true;
    }

    public void declareWinner(Race race) {
        List<Horse> horses = participantDao.getParticipatingHorses(race.getRace_id());
        if (horses.size() == 0) {
            raceRepository.delete(race);
        } else {
            Random random = new Random();
            race.setWinner(horses.get(random.nextInt(horses.size())));
            raceRepository.save(race);
        }
    }

    public List<Race> getUserRaceWins(int user) {
        return getPreviousRaces().stream()
                .filter(race -> race.getWinner().getUserid().getUser_id() == user)
                .collect(Collectors.toList());
    }
}
