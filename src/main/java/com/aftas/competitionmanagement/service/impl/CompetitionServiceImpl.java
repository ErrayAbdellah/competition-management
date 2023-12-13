package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.CompetitionDTO;
import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.repository.ICompetitionRepo;
import com.aftas.competitionmanagement.service.ICompetitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompetitionServiceImpl implements ICompetitionService {

    private final ModelMapper modelMapper;
    private final ICompetitionRepo competitionRepo;
    @Override
    public void createCompetition(CompetitionDTO competitionDTO) {
        if (!competitionRepo.existsCompetitionByCode(competitionDTO.getCode())) {
            Competition competition = modelMapper.map(competitionDTO, Competition.class);
            competitionRepo.save(competition);
        } else {
            throw new DuplicateCompetitionException("Competition code already exists: " + competitionDTO.getCode());
        }
    }

    @Override
    public List<CompetitionDTO> getScheduledCompetitions() {
        List<Competition> Competitions = competitionRepo.findAll();
        return Competitions.stream()
                .map(competition -> modelMapper.map(competition,CompetitionDTO.class))
                .collect(Collectors.toList());
    }

    public class DuplicateCompetitionException extends RuntimeException {
        public DuplicateCompetitionException(String message) {
            super(message);
        }
    }
}