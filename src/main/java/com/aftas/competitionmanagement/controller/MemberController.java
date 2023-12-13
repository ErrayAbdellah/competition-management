package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.MemberDTO;
import com.aftas.competitionmanagement.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api-member")
public class MemberController {

    private final IMemberService memberService ;

    @GetMapping("/all")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<MemberDTO> memberDTOs = memberService.getAllMembers();
        if (memberDTOs.isEmpty()) {
            return new ResponseEntity<>(memberDTOs,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(memberDTOs, HttpStatus.OK);
    }

    @GetMapping("/search-by-number")
    public ResponseEntity<MemberDTO> searchByNumber(@RequestParam Integer num) {
            MemberDTO member = memberService.searchMembersByNumber(num);
            return new ResponseEntity(member, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addMember(@Valid @RequestBody MemberDTO memberDTO) {
//        try {
            memberService.save(memberDTO);
            return new ResponseEntity<>("Member added successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to add member: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }

}
