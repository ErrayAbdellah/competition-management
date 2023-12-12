package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILevelRepo extends JpaRepository<Level,Long> {
}
