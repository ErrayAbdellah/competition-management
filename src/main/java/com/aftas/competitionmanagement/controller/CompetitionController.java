package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.CompetitionDTO;
import com.aftas.competitionmanagement.service.ICompetitionService;
import com.aftas.competitionmanagement.service.impl.CompetitionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-competition")
public class CompetitionController {
    private final ICompetitionService competitionService;

    @GetMapping("/all")
    public ResponseEntity<List<CompetitionDTO>> getScheduledCompetitions() {
        List<CompetitionDTO> scheduledCompetitions = competitionService.getScheduledCompetitions();
        return new ResponseEntity<>(scheduledCompetitions, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createCompetition(@RequestBody CompetitionDTO competitionDTO) {
        try {
            competitionService.createCompetition(competitionDTO);
            return new ResponseEntity<>("Competition created successfully", HttpStatus.CREATED);
        } catch (CompetitionServiceImpl.DuplicateCompetitionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create competition: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
