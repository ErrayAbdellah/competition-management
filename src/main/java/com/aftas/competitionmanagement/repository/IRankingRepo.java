package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRankingRepo extends JpaRepository<Ranking,Long> {
}
