package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Fish;
import com.aftas.competitionmanagement.entity.Hunting;
import com.aftas.competitionmanagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHuntingRepo extends JpaRepository<Hunting,Long> {
    Optional<Hunting> findByCompetitionAndMemberAndFish(Competition competition, Member member, Fish fish);

    @Query("SELECT SUM(l.points * h.numberOfFish) FROM Hunting h " +
            "JOIN h.fish f " +
            "JOIN f.level l " +
            "WHERE h.member.id = :memberId AND h.competition.id = :competitionId")
    int calculateScoreForMember(@Param("memberId") long memberId, @Param("competitionId") long competitionId);
}
