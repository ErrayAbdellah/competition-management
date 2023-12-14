package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Fish;
import com.aftas.competitionmanagement.entity.Hunting;
import com.aftas.competitionmanagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHuntingRepo extends JpaRepository<Hunting,Long> {
    Optional<Hunting> findByCompetitionAndMemberAndFish(Competition competition, Member member, Fish fish);

}
