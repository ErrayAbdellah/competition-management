package com.aftas.competitionmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCompetition implements Serializable{
    @Column(name = "member_id")
    private long memberID;
    @Column(name = "competition_id")
    private long competitionID;
}
