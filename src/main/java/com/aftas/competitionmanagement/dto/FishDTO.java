package com.aftas.competitionmanagement.dto;

import com.aftas.competitionmanagement.entity.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FishDTO {
    private long id;
    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "Fish cannot be blank")
    private double averageWeight;
    private Level level ;
}
