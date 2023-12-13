package com.aftas.competitionmanagement.service;

import org.springframework.stereotype.Repository;

@Repository
public interface IRankingService {
    void registerMemberForCompetition(Long memberId, Long competitionId);
}
