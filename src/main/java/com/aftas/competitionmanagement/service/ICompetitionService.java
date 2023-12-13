package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.CompetitionDTO;

import java.util.List;

public interface ICompetitionService {
    void createCompetition(CompetitionDTO competitionDTO);
    List<CompetitionDTO> getAllCompetitions();
}
