package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.MemberDTO;
import com.aftas.competitionmanagement.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IMemberService {
    String save(MemberDTO memberDTO);
    MemberDTO searchMembersByNumber(int searchTerm);
    List<MemberDTO> getAllMembers();
    List<MemberDTO> searchByNameOrFamilyName(String name, String familyName);
    Page<Member> getAllMembersPaginated(Pageable pageable);
}
