package com.example.telia.controller;

import com.example.telia.dao.HorseDao;
import com.example.telia.model.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HorseController {

    private final HorseDao horseDao;

    @Autowired
    public HorseController(HorseDao horseDao) {
        this.horseDao = horseDao;
    }

    @GetMapping("/horse/all")
    public List<Horse> getUsers() {
        return horseDao.getAllHorses();
    }

    @PostMapping("/horse/new")
    public Horse addUser(@RequestBody Horse horse) {
        return horseDao.addNewHorse(horse);
    }

    @GetMapping("/horse/all/{user_id}")
    public List<Horse> getUserHorses(@PathVariable int user_id) {
        return horseDao.getUserHorses(user_id);
    }

    @DeleteMapping("/horse/{horse_id}")
    public void removeUserHorse(@PathVariable int horse_id) {
        horseDao.removeUserHorse(horse_id);
    }

}
