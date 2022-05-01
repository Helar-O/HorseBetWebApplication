package com.example.telia.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "bet")
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bet_id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userid;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "participant_id", nullable = false)
    private Participant participantid;

    public Bet(User userid, Participant participantid) {
        this.userid = userid;
        this.participantid = participantid;
    }

    public int getBet_id() {
        return bet_id;
    }

    public void setBet_id(int bet_id) {
        this.bet_id = bet_id;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Participant getParticipantid() {
        return participantid;
    }

    public void setParticipantid(Participant participantid) {
        this.participantid = participantid;
    }

    public Bet() { }

}
