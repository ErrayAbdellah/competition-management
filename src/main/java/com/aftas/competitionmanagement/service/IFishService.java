package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.FishDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IFishService {
    void addFish(FishDTO fishDTO);

    List<FishDTO> getAllfish();
}
