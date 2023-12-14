package com.aftas.competitionmanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.aftas.competitionmanagement.entity.MemberCompetition;

@Entity @ToString
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Table(name = "ranking")
public class Ranking {
    @EmbeddedId
    private MemberCompetition id;

    private int rank;
    private int score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @MapsId("memberID")
    @JsonIgnore
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionID")
    @JsonIgnore
    private Competition competition;
}
