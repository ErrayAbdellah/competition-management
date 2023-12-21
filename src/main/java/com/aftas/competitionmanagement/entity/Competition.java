package com.aftas.competitionmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

@Entity @ToString
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Table(name = "competition")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private Date date;
    private Time startTime;
    private Time endTime;
    private int numberOfParticipants;
    private String location;
    private double amount;

    @OneToMany(mappedBy = "competition")
    @JsonManagedReference
    private Set<Ranking> rankings;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.REMOVE)
    private Set<Hunting> huntings ;

}
