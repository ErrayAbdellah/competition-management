package com.aftas.competitionmanagement.dto;

import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Fish;
import com.aftas.competitionmanagement.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HuntingDTO {
    private long id;

    @Positive(message = "Number of fish must be a positive number")
    private int numberOfFish;

    @NotNull(message = "Fish cannot be null")
    private FishDTO fish;

    @NotNull(message = "Member cannot be null")
    private String memberCode;

    @NotNull(message = "Competition cannot be null")
    private long competitionId;
}
