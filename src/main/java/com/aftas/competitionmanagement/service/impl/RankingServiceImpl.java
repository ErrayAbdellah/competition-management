package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.RankingDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RankingServiceImpl implements IRankingService {
    private final ICompetitionRepo competitionRepo;
    private final IRankingRepo rankingRepo;
    private final IMemberRepo memberRepo;
    private final ModelMapper modelMapper;
    @Override
    public void registerMemberForCompetition(String identityNumber, Long competitionId) {
        Member member = memberRepo.findByIdentityNumber(identityNumber)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with identity number: " + identityNumber));

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

    @Override
    public List<RankingDTO> updateRanking(Long competitionId) {
        List<Ranking> rankings = rankingRepo.findByCompetitionId(competitionId);
        rankings.sort((r1, r2) -> Long.compare(r2.getScore(), r1.getScore()));

        int rank = 1;
        for (Ranking ranking : rankings) {
            ranking.setRank(rank++);
            rankingRepo.save(ranking);
        }

        return rankings.stream()
                .map(ranking -> modelMapper.map(ranking, RankingDTO.class))
                .collect(Collectors.toList());
    }


}
