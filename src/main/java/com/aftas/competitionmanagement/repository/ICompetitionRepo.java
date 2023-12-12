package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompetitionRepo extends JpaRepository<Competition,Long> {
    boolean existsCompetitionByCode(String code);
}
