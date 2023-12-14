package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Fish;
import com.aftas.competitionmanagement.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILevelRepo extends JpaRepository<Level,Long> {
    List<Level> findLevelByPoints(int points);

    Optional<Level> findByCode(int id);
}
