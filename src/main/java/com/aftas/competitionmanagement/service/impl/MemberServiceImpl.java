package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.MemberDTO;
import com.aftas.competitionmanagement.entity.Member;
import com.aftas.competitionmanagement.exception.RecordNotFoundException;
import com.aftas.competitionmanagement.repository.IMemberRepo;
import com.aftas.competitionmanagement.service.IMemberService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements IMemberService {

    private final ModelMapper modelMapper;
    private final IMemberRepo memberRepo ;
    @Override
    public String save(MemberDTO memberDTO) {
        Member member = modelMapper.map(memberDTO, Member.class);
        memberRepo.save(member);
        return "Member saved successfully";
    }

    @Override
    public MemberDTO searchMembersByNumber(int num) {
        Optional<Member> member = Optional.ofNullable(memberRepo.findMembersByNum(num));
        return  member.map(m -> modelMapper.map(m, MemberDTO.class)).orElseThrow(()->  new RecordNotFoundException("this Member not found :- " + num));
    }

    @Override
    public List<MemberDTO> getAllMembers() {
        List<Member> members = memberRepo.findAll();
        return members.stream()
                .map(member -> modelMapper.map(member, MemberDTO.class))
                .collect(Collectors.toList());
    }

}
