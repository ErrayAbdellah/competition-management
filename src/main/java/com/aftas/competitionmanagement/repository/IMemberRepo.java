package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMemberRepo extends JpaRepository<Member,Long> {
    Member findMembersByNum(int num);

}
