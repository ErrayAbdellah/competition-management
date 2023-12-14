package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.HuntingDTO;
import com.aftas.competitionmanagement.entity.Hunting;
import com.aftas.competitionmanagement.service.IHuntingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api-hunting")
@RequiredArgsConstructor
public class HuntingController {
    private final IHuntingService huntingService;

    @PostMapping
    public ResponseEntity<String> huntingFish(@Valid @RequestBody HuntingDTO huntingDto) {
            String result = huntingService.huntingFish(huntingDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
