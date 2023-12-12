package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHuntingRepo extends JpaRepository<Hunting,Long> {
}
