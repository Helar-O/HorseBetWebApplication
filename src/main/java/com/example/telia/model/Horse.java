package com.example.telia.model;

import org.aspectj.lang.annotation.DeclareAnnotation;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;

@Entity
@Table(name = "horse")
public class Horse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int horse_id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userid;
    @Column(nullable = false)
    private String name;

    public Horse(User userid, String name) {
        this.userid = userid;
        this.name = name;
    }

    public int getHorse_id() {
        return horse_id;
    }

    public void setHorse_id(int horse_id) {
        this.horse_id = horse_id;
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Horse() {
    }

}
