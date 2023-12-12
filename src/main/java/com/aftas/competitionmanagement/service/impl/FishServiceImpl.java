package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.FishDTO;
import com.aftas.competitionmanagement.entity.Fish;
import com.aftas.competitionmanagement.repository.IFishRepo;
import com.aftas.competitionmanagement.service.IFishService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FishServiceImpl implements IFishService {
    private final ModelMapper modelMapper;
    private final IFishRepo fishRepo;
    @Override
    public void addFish(FishDTO fishDTO) {
        Fish fish = modelMapper.map(fishDTO,Fish.class);
        fishRepo.save(fish);
    }
}
