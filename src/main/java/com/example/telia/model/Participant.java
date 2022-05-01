package com.example.telia.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int participant_id;
    @OneToOne
    @JoinColumn(name = "race_id", nullable = false)
    private Race raceid;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "horse_id", nullable = false)
    private Horse horseid;
    @Column(nullable = false)
    private String color;

    public Participant(Race raceid, Horse horseid, String color) {
        this.raceid = raceid;
        this.horseid = horseid;
        this.color = color;
    }

    public Participant() {
    }

    public int getParticipant_id() {
        return participant_id;
    }

    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }

    public Race getRaceid() {
        return raceid;
    }

    public void setRaceid(Race raceid) {
        this.raceid = raceid;
    }

    public Horse getHorseid() {
        return horseid;
    }

    public void setHorseid(Horse horseid) {
        this.horseid = horseid;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}