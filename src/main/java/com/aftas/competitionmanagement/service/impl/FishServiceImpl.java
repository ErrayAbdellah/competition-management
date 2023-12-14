package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.FishDTO;
import com.aftas.competitionmanagement.entity.Fish;
import com.aftas.competitionmanagement.entity.Level;
import com.aftas.competitionmanagement.repository.IFishRepo;
import com.aftas.competitionmanagement.repository.ILevelRepo;
import com.aftas.competitionmanagement.service.IFishService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FishServiceImpl implements IFishService {
    private final ModelMapper modelMapper;
    private final IFishRepo fishRepo;
    private final ILevelRepo levelRepo;

    @Override
    public void addFish(FishDTO fishDTO) {
        Optional<Level> levelExist = levelRepo.findByCode(fishDTO.getLevel().getCode());
        if (levelExist.isEmpty()) {
            throw new IllegalArgumentException("Level not found with code: " + fishDTO.getLevel().getCode());
        }
        Fish fish = modelMapper.map(fishDTO, Fish.class);
        fish.setLevel(levelExist.get());
        fishRepo.save(fish);
    }
}
