package com.aftas.competitionmanagement.service.impl;


import com.aftas.competitionmanagement.dto.HuntingDTO;
import com.aftas.competitionmanagement.entity.*;
import com.aftas.competitionmanagement.repository.*;
import com.aftas.competitionmanagement.service.IHuntingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class HuntingServiceImpl implements IHuntingService {
    private final IHuntingRepo huntingRepo;
    private final IFishRepo fishRepo;
    private final IMemberRepo memberRepo;
    private final ICompetitionRepo competitionRepo;
    private final IRankingRepo rankingRepo;
    private final ModelMapper modelMapper;
    @Override
    public String huntingFish(HuntingDTO huntingDto) {
        Fish fish = fishRepo.findByName(huntingDto.getFish().getName())
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this fish does not exist!"));

        Competition competition = competitionRepo.findById(huntingDto.getCompetitionId())
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this competition does not exist!"));

        Member member = memberRepo.findByIdentityNumber(huntingDto.getMemberCode())
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this member does not exist!"));

        if (huntingDto.getFish().getAverageWeight() <= fish.getAverageWeight()) {
            throw new IllegalArgumentException("The weight of the hunted fish exceeds the average weight!");
        }

        rankingRepo.findByMemberAndCompetition(member, competition)
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this member is not registered yet in the competition!"));

        Optional<Hunting> existingHunting = huntingRepo.findByCompetitionAndMemberAndFish(competition, member, fish);

        if (existingHunting.isPresent()) {

            Hunting huntingData = existingHunting.get();
            huntingData.setNumberOfFish(huntingData.getNumberOfFish() + 1);
//            huntingRepo.save(huntingData);
            addScor(huntingData);
            return "Member saved successfully" ;
        } else {

            Hunting newHunting = Hunting.builder()
                    .fish(fish)
                    .competition(competition)
                    .member(member)
                    .numberOfFish(1)
                    .build();
            addScor(newHunting);
            return "Member saved successfully";
        }
    }
    public void addScor(Hunting hunting){
        huntingRepo.save(hunting);
        Ranking ranking = rankingRepo.findByMemberAndCompetition(hunting.getMember(),hunting.getCompetition())
                .orElseThrow(()->new IllegalArgumentException("Sorry, this member is not registered yet in the competition!"));
        ranking.setScore(calculateScore(hunting));
        rankingRepo.save(ranking);


    }

    public int calculateScore(Hunting hunting) {
        return huntingRepo.calculateScoreForMember(hunting.getMember().getId(),hunting.getCompetition().getId());
    }


}
