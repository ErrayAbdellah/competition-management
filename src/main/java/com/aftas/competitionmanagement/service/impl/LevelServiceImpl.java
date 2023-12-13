package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.LevelDTO;
import com.aftas.competitionmanagement.entity.Level;
import com.aftas.competitionmanagement.repository.ILevelRepo;
import com.aftas.competitionmanagement.service.ILevelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LevelServiceImpl implements ILevelService {

    private final ModelMapper modelMapper;
    private final ILevelRepo levelRepo;
    @Override
    public void addLevel(LevelDTO levelDTO) {
        Level level = modelMapper.map(levelDTO,Level.class);
        levelRepo.save(level);
    }
}
