package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.FishDTO;
import com.aftas.competitionmanagement.repository.IHuntingRepo;
import com.aftas.competitionmanagement.service.IFishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-fish")
public class FishController {
    private final IFishService fishService;
    @PostMapping
    public ResponseEntity<String> addFish(@RequestBody FishDTO fishDTO) {
        try {
            fishService.addFish(fishDTO);
            return new ResponseEntity<>("Fish added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add fish: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<FishDTO>> getAllFish(){
        List<FishDTO> fishDTOS = fishService.getAllfish();
        return new ResponseEntity<>(fishDTOS, HttpStatus.OK);
    }
    private final IHuntingRepo huntingRepo ;
//    @GetMapping("/scor")
//    public void getScor(){
//        System.out.println(huntingRepo.calculeScor(1L));
////        return huntingRepo.calculeScor(1L);
//    }
//    @GetMapping("/score/{memberId}")
//    public ResponseEntity<Long> getMemberScore(@PathVariable Long memberId) {
//        int score = huntingRepo.calculateScoreForMember(memberId,7);
//        if (score != null) {
//            System.out.println(score);
//            return ResponseEntity.ok(score);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
}
