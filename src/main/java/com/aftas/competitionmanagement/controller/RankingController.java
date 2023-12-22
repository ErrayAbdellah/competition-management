package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.RankingDTO;
import com.aftas.competitionmanagement.entity.Ranking;
import com.aftas.competitionmanagement.service.IRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-ranking")
public class RankingController {
    private final IRankingService rankingService;
    @PostMapping("/{competitionId}/register/{identityNumber}")
    public ResponseEntity<String> registerMemberForCompetition(
            @PathVariable Long competitionId,
            @PathVariable String identityNumber) {

        System.out.println(competitionId+" " + identityNumber);
            rankingService.registerMemberForCompetition(identityNumber, competitionId);
            return new ResponseEntity<String>("Code registered for the competition successfully", HttpStatus.CREATED);
    }

    @GetMapping("/updateRankings/{competitionId}")
    public ResponseEntity<List<RankingDTO>> updateRankings(@PathVariable Long competitionId) {
        List<RankingDTO> updatedRankings = rankingService.updateRanking(competitionId);
        return ResponseEntity.ok(updatedRankings);
    }
}
