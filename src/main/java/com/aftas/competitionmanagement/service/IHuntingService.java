package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.HuntingDTO;
import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Fish;
import com.aftas.competitionmanagement.entity.Hunting;
import com.aftas.competitionmanagement.entity.Member;
import org.springframework.stereotype.Service;

@Service
public interface IHuntingService {

    String huntingFish(HuntingDTO huntingDto);
}
