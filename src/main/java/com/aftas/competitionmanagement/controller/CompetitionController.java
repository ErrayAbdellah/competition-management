package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.CompetitionDTO;
import com.aftas.competitionmanagement.service.ICompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-competition")
@CrossOrigin(origins = "http://localhost:4200")
public class CompetitionController {
    private final ICompetitionService competitionService;

    @GetMapping("/all")
    public ResponseEntity<List<CompetitionDTO>> getScheduledCompetitions() {
        List<CompetitionDTO> scheduledCompetitions = competitionService.getAllCompetitions();
        return new ResponseEntity<>(scheduledCompetitions, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> createCompetition(@Valid @RequestBody CompetitionDTO competitionDTO) {
        competitionService.createCompetition(competitionDTO);
        return new ResponseEntity<> ("Competition created successfully", HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompetition(@PathVariable Long id, @RequestBody CompetitionDTO competitionDTO) {
        try {
            competitionService.updateCompetition(id, competitionDTO);
            return ResponseEntity.ok("Competition updated successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompetition(@PathVariable Long id) {
        try {
            competitionService.deleteCompetition(id);
            return ResponseEntity.ok("Competition deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
