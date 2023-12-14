package com.aftas.competitionmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity @ToString
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Table(name = "hunting")
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int numberOfFish;

    @ManyToOne
    @JoinColumn(name = "fish_id")
    @JsonIgnore
    private Fish fish;

    @ManyToOne
    @JoinColumn(name = "member_num")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @JsonIgnore
    private Competition competition;
}
