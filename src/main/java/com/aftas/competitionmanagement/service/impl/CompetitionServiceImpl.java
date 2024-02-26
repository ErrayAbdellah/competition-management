package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.CompetitionDTO;
import com.aftas.competitionmanagement.entity.Competition;
import com.aftas.competitionmanagement.entity.Member;
import com.aftas.competitionmanagement.entity.Ranking;
import com.aftas.competitionmanagement.repository.ICompetitionRepo;
import com.aftas.competitionmanagement.repository.IMemberRepo;
import com.aftas.competitionmanagement.service.ICompetitionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompetitionServiceImpl implements ICompetitionService {

    private final ModelMapper modelMapper;
    private final ICompetitionRepo competitionRepo;
    private final IMemberRepo memberRepo;
    @Override
    public void createCompetition(CompetitionDTO competitionDTO) {
        if (!competitionRepo.existsCompetitionByCode(competitionDTO.getCode())) {
            Competition competition = modelMapper.map(competitionDTO, Competition.class);
            competitionRepo.save(competition);
        } else {
            throw new IllegalArgumentException("Competition code already exists: " + competitionDTO.getCode());
        }
    }

    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        List<Competition> Competitions = competitionRepo.findAll();
        return Competitions.stream()
                .map(competition -> modelMapper.map(competition,CompetitionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateCompetition(Long id, CompetitionDTO competitionDTO) {
        Competition competition = competitionRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Competition not found with ID: " + id));

        modelMapper.map(competitionDTO, competition);

        competitionRepo.save(competition);
    }
    @Override
    public void deleteCompetition(Long id) {
        competitionRepo.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Competition not found with ID: " + id));
        competitionRepo.deleteById(id);
    }
    @Override
    public List<CompetitionDTO> getCompetitionsForAuthenticatedUser() {
        //Retrieve the Authentication object
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Extract the email or username of the authenticated user
        String userEmail = authentication.getName(); // Assuming email is used for authentication
        String message = "Competitions for current user: " + userEmail;
        // Step 3: Query the database to find the member by email
        Member member = memberRepo.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Member not found for email: " + userEmail));

        // Retrieve the competitions associated with the member's rankings
        List<Competition> competitions = member.getRankings().stream()
                .map(Ranking::getCompetition)
                .distinct()
                .toList();

        if (competitions.isEmpty()) {
            throw new RuntimeException("Member with email " + userEmail + " is not in any competition");
        }
        // Convert Competition entities to CompetitionDTOs
//        List<CompetitionDTO> competitionDTOs = competitions.stream()
//                .map(this::convertToCompetitionDTO)
//                .collect(Collectors.toList());


        return competitions.stream()
                .map(competition -> modelMapper.map(competition,CompetitionDTO.class))
                .collect(Collectors.toList());
    }
}
