package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.FishDTO;
import com.aftas.competitionmanagement.service.IFishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-fish")
public class FishController {
    private final IFishService fishService;
    @PostMapping("/add")
    public ResponseEntity<String> addFish(@RequestBody FishDTO fishDTO) {
        try {
            fishService.addFish(fishDTO);
            return new ResponseEntity<>("Fish added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add fish: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
