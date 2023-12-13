package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Member;
import com.aftas.competitionmanagement.entity.MemberCompetition;
import com.aftas.competitionmanagement.entity.Ranking;
import com.aftas.competitionmanagement.repository.ICompetitionRepo;
import com.aftas.competitionmanagement.repository.IMemberRepo;
import com.aftas.competitionmanagement.repository.IRankingRepo;
import com.aftas.competitionmanagement.service.IRankingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class RankingServiceImpl implements IRankingService {
    private final ICompetitionRepo competitionRepo;
    private final IRankingRepo rankingRepo;
    private final IMemberRepo memberRepo;
    private final ModelMapper modelMapper;
    @Override
    public void registerMemberForCompetition(Long memberId, Long competitionId) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with ID: " + memberId));

        Competition competition = competitionRepo.findById(competitionId)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found with ID: " + competitionId));

        LocalDateTime competitionStart = competition.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime currentDateTime = LocalDateTime.now();

        if (currentDateTime.isAfter(competitionStart.minusHours(24))) {
            throw new IllegalStateException("Registration is closed. You cannot register for this competition.");
        }

        if (rankingRepo.existsByMemberAndCompetition(member, competition)) {
            throw new IllegalStateException("Member is already registered for the competition.");
        }

        Ranking ranking = Ranking.builder()
                .id(MemberCompetition.builder()
                        .competitionID(competition.getId())
                        .memberID(member.getId())
                        .build())
                .member(member)
                .competition(competition)
                .build();

        rankingRepo.save(ranking);
    }
}
