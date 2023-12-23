package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Member;
import com.aftas.competitionmanagement.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IRankingRepo extends JpaRepository<Ranking,Long> {
    boolean existsByMemberAndCompetition(Member member, Competition competition);
    Optional<Ranking> findByMemberAndCompetition(Member member, Competition competition);
    List<Ranking>  findByCompetitionId(long competitionId);
}
