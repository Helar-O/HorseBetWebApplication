package com.example.telia.Dao;

import com.example.telia.model.Bet;
import com.example.telia.model.Participant;
import com.example.telia.model.Race;
import com.example.telia.repository.BetRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BetDao {

    private final BetRepository betRepository;
    private final ParticipantDao participantDao;
    private final RaceDao raceDao;

    public BetDao(BetRepository betRepository, ParticipantDao participantDao, RaceDao raceDao) {
        this.betRepository = betRepository;
        this.participantDao = participantDao;
        this.raceDao = raceDao;
    }

    public Bet addBet(Bet bet) throws Exception {
        if (!hasPlacedBet(bet)) {
            betRepository.save(bet);
            return bet;
        }
        throw new Exception("User already bet on this race");
    }

    public boolean hasPlacedBet(Bet bet) {
        Participant participant = participantDao.getParticipantById(bet.getParticipantid().getParticipant_id());
        return getBets().stream()
                .anyMatch(existingBet -> existingBet.getParticipantid().getRaceid().getRace_id() == participant.getRaceid().getRace_id() &&
                        existingBet.getUserid().getUser_id() == bet.getUserid().getUser_id());
    }

    public List<Bet> getBets() {
        return (List<Bet>) betRepository.findAll();
    }

    public Map<Integer, String> getUserOngoingBetHorseAndColor(int user_id) {
        Map<Integer, String> bets = new HashMap<>();
        for (Bet bet : getBets()) {
            if (bet.getParticipantid().getRaceid().getWinner() == null &&
                    bet.getUserid().getUser_id() == user_id) {
                bets.put(bet.getParticipantid().getRaceid().getRace_id(),
                        bet.getParticipantid().getColor() + " " + bet.getParticipantid().getHorseid().getName());
            }
        }
        return bets;
    }

    public ArrayList<Pair<Race, String>> getUserActiveBets(int user_id) {
        ArrayList<Pair<Race, String>> bets = new ArrayList<>();
        for (Bet bet : getBets()) {
            if (bet.getParticipantid().getRaceid().getWinner() == null &&
                    bet.getUserid().getUser_id() == user_id) {
                bets.add(Pair.of(bet.getParticipantid().getRaceid(),
                        (bet.getParticipantid().getColor() + " " + bet.getParticipantid().getHorseid().getName())));
            }
        }
        return bets;
    }

    public ArrayList<Pair<Race, String>> getUserWonBets(int user_id) {
        List<Race> previousRaces = raceDao.getPreviousRaces();
        ArrayList<Pair<Race, String>> bets = new ArrayList<>();
        for (Bet bet : getBets()) {
            if (previousRaces.contains(bet.getParticipantid().getRaceid()) &&
                    bet.getUserid().getUser_id() == user_id &&
                    bet.getParticipantid().getRaceid().getWinner().getUserid().getUser_id() == user_id) {
                bets.add(Pair.of(bet.getParticipantid().getRaceid(),
                        (bet.getParticipantid().getColor() + " " + bet.getParticipantid().getHorseid().getName())));
            }
        }
        return bets;
    }
}
