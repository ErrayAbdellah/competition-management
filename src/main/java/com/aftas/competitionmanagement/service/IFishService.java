package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.FishDTO;
import org.springframework.stereotype.Service;

@Service
public interface IFishService {
    void addFish(FishDTO fishDTO);
}
