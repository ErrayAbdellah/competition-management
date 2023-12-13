package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Member;
import com.aftas.competitionmanagement.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRankingRepo extends JpaRepository<Ranking,Long> {
    boolean existsByMemberAndCompetition(Member member, Competition competition);
}
