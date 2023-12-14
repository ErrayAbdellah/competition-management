package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.LevelDTO;
import com.aftas.competitionmanagement.entity.Level;
import com.aftas.competitionmanagement.service.ILevelService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-level")
public class LevelController {
    private final ILevelService levelService;

    @PostMapping
    public ResponseEntity<String> addLevel(@RequestBody LevelDTO levelDTO) {
            levelService.addLevel(levelDTO);
            return new ResponseEntity<>("Shooting level added successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<LevelDTO>> getAllLevel(){
        List<LevelDTO> levelDTOS = levelService.getAllLevel();
        return new ResponseEntity<>(levelDTOS,HttpStatus.OK);
    }

}
