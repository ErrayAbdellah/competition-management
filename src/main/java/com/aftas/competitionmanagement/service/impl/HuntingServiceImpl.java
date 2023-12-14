package com.aftas.competitionmanagement.service.impl;


import com.aftas.competitionmanagement.dto.HuntingDTO;
import com.aftas.competitionmanagement.entity.*;
import com.aftas.competitionmanagement.repository.*;
import com.aftas.competitionmanagement.service.IHuntingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
        // Validate and retrieve entities
        Fish fish = fishRepo.findByName(huntingDto.getFish().getName())
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this fish does not exist!"));

        Competition competition = competitionRepo.findById(huntingDto.getCompetitionId())
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this competition does not exist!"));

        Member member = memberRepo.findById(huntingDto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this member does not exist!"));

        // Check average weight
        if (huntingDto.getFish().getAverageWeight() >= fish.getAverageWeight()) {
            throw new IllegalArgumentException("The weight of the hunted fish exceeds the average weight!");
        }

        // Check if the member is registered for the competition
        rankingRepo.findByMemberAndCompetition(member, competition)
                .orElseThrow(() -> new IllegalArgumentException("Sorry, this member is not registered yet in the competition!"));

        // Check if the hunting record already exists
        Optional<Hunting> existingHunting = huntingRepo.findByCompetitionAndMemberAndFish(competition, member, fish);

        if (existingHunting.isPresent()) {
            // Update existing hunting record
            Hunting huntingData = existingHunting.get();
            huntingData.setNumberOfFish(huntingData.getNumberOfFish() + 1);
            huntingRepo.save(huntingData);
            return "Member saved successfully" ;
        } else {
            // Create new hunting record
            Hunting newHunting = Hunting.builder()
                    .fish(fish)
                    .competition(competition)
                    .member(member)
                    .numberOfFish(1)
                    .build();

            huntingRepo.save(newHunting);
            return "Member saved successfully";
        }

    }

}
