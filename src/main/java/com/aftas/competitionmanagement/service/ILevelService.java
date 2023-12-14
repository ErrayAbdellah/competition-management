package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.LevelDTO;
import com.aftas.competitionmanagement.entity.Level;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ILevelService {
    void addLevel(LevelDTO levelDTO);
    List<LevelDTO> getAllLevel();
    LevelDTO getLevelByCode(Integer code);
}
