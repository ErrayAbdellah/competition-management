package com.aftas.competitionmanagement.repository;

import com.aftas.competitionmanagement.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMemberRepo extends JpaRepository<Member,Long> {
    Member findMembersByNum(int num);
    @Query("SELECT m FROM Member m WHERE m.name = :name OR m.familyName = :familyName")
    List<Member> findByNameOrFamilyName(@Param("name") String name,
                                        @Param("familyName") String familyName);

    @Query("SELECT m FROM Member m WHERE m.identityNumber = :identityNumber")
    Optional<Member> findByIdentityNumber(@Param("identityNumber") String identityNumber);

    Optional<Member> findByEmail(String email);
}
