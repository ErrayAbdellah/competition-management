package com.aftas.competitionmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberCompetitionDTO {
    private long memberId;
    private long competitionId;
}
