package com.example.telia.controller;

import com.example.telia.dao.BetDao;
import com.example.telia.model.Bet;
import com.example.telia.model.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BetController {

    private final BetDao betDao;

    @Autowired
    public BetController(BetDao betDao) {
        this.betDao = betDao;
    }

    @PostMapping("/bet/new")
    public Bet addBet(@RequestBody Bet bet) throws Exception {
        return betDao.addBet(bet);
    }

    @GetMapping("/bet/all")
    public List<Bet> getBets() {
        return betDao.getBets();
    }

    @GetMapping("bet/{user_id}")
    public Map<Integer, String> getUserBetHorses(@PathVariable int user_id) {
        return betDao.getUserOngoingBetHorseAndColor(user_id);
    }

    @GetMapping("/bet/active/{user_id}")
    public ArrayList<Pair<Race, String>> getUserActiveBets(@PathVariable int user_id) {
        return betDao.getUserActiveBets(user_id);
    }

    @GetMapping("/bet/won/{user_id}")
    public ArrayList<Pair<Race, String>> getUserWonBets(@PathVariable int user_id) {
        return betDao.getUserWonBets(user_id);
    }
}
