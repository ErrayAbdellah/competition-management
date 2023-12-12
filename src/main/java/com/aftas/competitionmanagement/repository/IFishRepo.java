package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Fish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFishRepo extends JpaRepository<Fish,Long> {
}
