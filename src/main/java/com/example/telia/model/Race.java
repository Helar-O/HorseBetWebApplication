package com.example.telia.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "race")
public class Race {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int race_id;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalTime time;
    @OneToOne
    @JoinColumn(name = "horse_id")
    private Horse winner;

    public Race(@NonNull String place, @NonNull LocalDate date, @NonNull LocalTime time) {
        this.place = place;
        this.date = date;
        this.time = time;
    }

    public Race() {
    }

    public int getRace_id() {
        return race_id;
    }

    public void setRace_id(int race_id) {
        this.race_id = race_id;
    }

    @NonNull
    public String getPlace() {
        return place;
    }

    public void setPlace(@NonNull String place) {
        this.place = place;
    }

    @NonNull
    public LocalDate getDate() {
        return date;
    }

    public void setDate(@NonNull LocalDate date) {
        this.date = date;
    }

    @NonNull
    public LocalTime getTime() {
        return time;
    }

    public void setTime(@NonNull LocalTime time) {
        this.time = time;
    }

    public Horse getWinner() {
        return winner;
    }

    public void setWinner(Horse winner) {
        this.winner = winner;
    }
}
