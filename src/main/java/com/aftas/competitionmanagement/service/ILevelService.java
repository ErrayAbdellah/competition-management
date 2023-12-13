package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.LevelDTO;
import org.springframework.stereotype.Service;

@Service
public interface ILevelService {
    void addLevel(LevelDTO levelDTO);
}
