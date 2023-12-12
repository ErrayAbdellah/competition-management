package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.MemberDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMemberService {
    String save(MemberDTO memberDTO);
    MemberDTO searchMembersByNumber(int searchTerm);
    List<MemberDTO> getAllMembers();

}
