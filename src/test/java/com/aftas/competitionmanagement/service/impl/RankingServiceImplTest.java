package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.RankingDTO;
import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Member;
import com.aftas.competitionmanagement.entity.Ranking;
import com.aftas.competitionmanagement.enums.IdentityDocumentTyp;
import com.aftas.competitionmanagement.repository.IRankingRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RankingServiceImplTest {
    @Mock
    IRankingRepo rankingRepo;

    @Mock
   ModelMapper modelMapper;
    @Mock
    Competition competition;
    @InjectMocks
    RankingServiceImpl rankingService;
    List<Member> members;
    List<Ranking> rankings;

//    RankingServiceImplTest(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        competition = Competition.builder()
                .id(1L)
                .code("COMP-001")
                .date(new Date())
                .startTime(Time.valueOf("09:00:00"))
                .endTime(Time.valueOf("15:00:00"))
                .numberOfParticipants(30)
                .location("Mirleft")
                .amount(1000.0)
                .build();

        members = List.of(
                Member.builder()
                        .id(1L)
                        .num(101)
                        .name("John")
                        .familyName("Doe")
                        .accessionDate(new Date())
                        .nationality("Moroccan")
                        .identityDocument(IdentityDocumentTyp.PASSPORT)
                        .identityNumber("ID123456")
                        .build(),
                Member.builder()
                        .id(2L)
                        .num(102)
                        .name("Jane")
                        .familyName("Smith")
                        .accessionDate(new Date())
                        .nationality("Moroccan")
                        .identityDocument(IdentityDocumentTyp.CART_RESIDENCE)
                        .identityNumber("ID654321")
                        .build(),
                Member.builder()
                        .id(3L)
                        .num(103)
                        .name("Ahmed")
                        .familyName("Khan")
                        .accessionDate(new Date())
                        .nationality("Moroccan")
                        .identityDocument(IdentityDocumentTyp.CIN)
                        .identityNumber("DL789123")
                        .build()
        );
        int[] score={30,5,120};
        final int[] index={0};
        rankings= new ArrayList<>();
        members.forEach(m->{
            this.rankings.add(new Ranking().builder().member(m).score(score[index[0]]).competition(competition).build());
            index[0]++;
        });
    }
    @Test
    public void updateRanking_ReturnRankUpdated(){
        long competitionId=1L;
        doReturn(rankings).when(rankingRepo).findByCompetitionId(competitionId);
        int[] rank={1};
        List<Ranking> rankingsExpected = rankings.stream()
                .sorted((r1, r2) -> Long.compare(r2.getScore(), r1.getScore()))
                .map(re->{
                    re.setRank(rank[0]++);
                    return re;
                })
                .toList();
      List<RankingDTO> expectedDto=rankingsExpected.stream()
                .map(ranking -> modelMapper.map(rankingsExpected, RankingDTO.class))
                .collect(Collectors.toList());
      assertEquals(expectedDto, rankingService.updateRanking(competitionId));

    }
}
//    public List<RankingDTO> updateRanking(Long competitionId) {
//        List<Ranking> rankings = rankingRepo.findByCompetitionId(competitionId);
//        rankings.sort((r1, r2) -> Long.compare(r2.getScore(), r1.getScore()));
//
//        int rank = 1;
//        for (Ranking ranking : rankings) {
//            ranking.setRank(rank++);
//            rankingRepo.save(ranking);
//        }
//
//        return rankings.stream()
//                .map(ranking -> modelMapper.map(ranking, RankingDTO.class))
//                .collect(Collectors.toList());
//    }