package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.LevelDTO;
import com.aftas.competitionmanagement.service.ILevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-level")
public class LevelController {
    private final ILevelService levelService;

    @PostMapping
    public ResponseEntity<String> addLevel(@RequestBody LevelDTO levelDTO) {
        try {
            levelService.addLevel(levelDTO);
            return new ResponseEntity<>("Shooting level added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add shooting level: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
