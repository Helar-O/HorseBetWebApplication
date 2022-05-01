package com.example.telia.Dao;

import com.example.telia.model.Horse;
import com.example.telia.repository.HorseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class HorseDao {

    private final HorseRepository horseRepository;

    public HorseDao(HorseRepository horseRepository) {
        this.horseRepository = horseRepository;
    }

    public List<Horse> getAllHorses() {
        return (List<Horse>) horseRepository.findAll();
    }

    public Horse addNewHorse(Horse horse) {
        horseRepository.save(horse);
        return horse;
    }

    public List<Horse> getUserHorses(int user_id) {
        return getAllHorses().stream()
                .filter(horse -> horse.getUserid().getUser_id() == user_id)
                .collect(Collectors.toList());
    }

    public void removeUserHorse(int horse_id) {
        horseRepository.delete(Objects.requireNonNull(getAllHorses().stream()
                .filter(horse -> horse.getHorse_id() == horse_id)
                .findFirst().orElse(null)));
    }
}
