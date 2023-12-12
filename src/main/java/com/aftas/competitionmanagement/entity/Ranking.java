package com.aftas.competitionmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @ToString
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Table(name = "ranking")
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rank;
    private int score;

    @ManyToOne
    @JoinColumn(name = "member_num")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;
}
