package com.aftas.competitionmanagement.controller;

import com.aftas.competitionmanagement.dto.MemberDTO;
import com.aftas.competitionmanagement.entity.Member;
import com.aftas.competitionmanagement.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return new ResponseEntity<>(memberDTOs, HttpStatus.OK);
    }


    @GetMapping("/search-by-number")
    public ResponseEntity<MemberDTO> searchByNumber(@RequestParam Integer num) {
            MemberDTO member = memberService.searchMembersByNumber(num);
            return new ResponseEntity(member, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<String> addMember(@Valid @RequestBody MemberDTO memberDTO) {
            memberService.save(memberDTO);
            return new ResponseEntity<>("Member added successfully", HttpStatus.OK);

    }
    @PostMapping("/paginate")
    public ResponseEntity getAllMembersPaginate(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {

            return new ResponseEntity<>(memberService.getAllMembersPaginated(PageRequest.of(page, size)),HttpStatus.OK);
    }

    @GetMapping("/searchByNumber/{num}")
    public ResponseEntity<MemberDTO> getMemberByNumber(@PathVariable int num) {
        return ResponseEntity.ok(memberService.searchMembersByNumber(num));
    }
    @GetMapping("/search")
    public ResponseEntity<List<MemberDTO>> searchMembers(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String familyName) {
        List<MemberDTO> members = memberService.searchByNameOrFamilyName(name, familyName);
        return ResponseEntity.ok(members);
    }

}
