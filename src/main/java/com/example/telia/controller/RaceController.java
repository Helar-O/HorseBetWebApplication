package com.example.telia.controller;

import com.example.telia.dao.RaceDao;
import com.example.telia.model.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RaceController {

    private final RaceDao raceDao;

    @Autowired
    public RaceController(RaceDao raceDao) {
        this.raceDao = raceDao;
    }

    @GetMapping("/race/all")
    public List<Race> getRaces() {
        return raceDao.getAllRaces();
    }

    @PostMapping("/race/new")
    public Race addRace(@RequestBody Race race) throws Exception {
        return raceDao.addRace(race);
    }

    @GetMapping("/race/ongoing")
    public List<Race> getOngoingRaces() {
        return raceDao.getOngoingRaces();
    }

    @GetMapping("/race/history")
    public List<Race> getPreviousRaces() {
        return raceDao.getPreviousRaces();
    }

    @GetMapping("/race/wins/{user_id}")
    public List<Race> getUserRaceWins(@PathVariable int user_id) {
        return raceDao.getUserRaceWins(user_id);
    }
}
