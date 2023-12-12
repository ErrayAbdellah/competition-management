package com.aftas.competitionmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Time;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionDTO {
    private long id;
    private String code;
    @NotNull(message = "Date cannot be null")
    @Future(message = "Date must be in the future")
    private Date date;

    @NotNull(message = "Start time cannot be null")
    private Time startTime;

    @NotNull(message = "End time cannot be null")
    private Time endTime;
    @NotBlank(message = "Number Of Participants cannot be blank")
    @Positive(message = "Number of participants must be positive")
    private int numberOfParticipants;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @Positive(message = "Amount must be positive")
    private double amount;
}
