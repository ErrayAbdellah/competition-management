package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.RankingDTO;
import com.aftas.competitionmanagement.entity.Ranking;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRankingService {
    void registerMemberForCompetition(String identityNumber, Long competitionId);
    List<RankingDTO> updateRanking(Long competitionId);
}
