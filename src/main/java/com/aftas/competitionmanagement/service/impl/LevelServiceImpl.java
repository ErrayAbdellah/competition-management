package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.LevelDTO;
import com.aftas.competitionmanagement.entity.Level;
import com.aftas.competitionmanagement.repository.ILevelRepo;
import com.aftas.competitionmanagement.service.ILevelService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<LevelDTO> getAllLevel() {
        List<Level> levelList = levelRepo.findAll();
        if(levelList.isEmpty()){
            throw new IllegalArgumentException("Not Found Any Level");
        }
        return levelList.stream().map(level -> modelMapper.map(level,LevelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public LevelDTO getLevelByCode(Integer code) {
        return null;
    }
}
