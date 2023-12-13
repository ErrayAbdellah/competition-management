package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.repository.IRankingRepo;
import com.aftas.competitionmanagement.service.IRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-ranking")
public class RankingController {
    private final IRankingService rankingService;
    @PostMapping("/{competitionId}/register/{memberId}")
    public ResponseEntity<String> registerMemberForCompetition(
            @PathVariable Long competitionId,
            @PathVariable Long memberId) {

        System.out.println(competitionId+" " + memberId);
            rankingService.registerMemberForCompetition(memberId, competitionId);
            return new ResponseEntity<>("Member registered for the competition successfully", HttpStatus.CREATED);
    }
}
