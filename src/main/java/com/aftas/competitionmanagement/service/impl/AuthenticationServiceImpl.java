package com.aftas.competitionmanagement.service.impl;

import com.aftas.competitionmanagement.dto.request.AuthenticationRequest;
import com.aftas.competitionmanagement.dto.request.RegisterRequest;
import com.aftas.competitionmanagement.dto.response.AuthenticationResponse;
import com.aftas.competitionmanagement.entity.Member;
import com.aftas.competitionmanagement.entity.Role;
import com.aftas.competitionmanagement.entity.Token;
import com.aftas.competitionmanagement.enums.TokenType;
import com.aftas.competitionmanagement.repository.IMemberRepo;
import com.aftas.competitionmanagement.repository.ITokenRepo;
import com.aftas.competitionmanagement.service.AuthenticationService;
import com.aftas.competitionmanagement.service.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final IMemberRepo memberRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ITokenRepo tokenRepo;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Role role = new Role();
        role.setId(1);
        Member member = Member.builder()
                .name(request.getFirstName())
                .familyName(request.getLastName())
                .email(request.getEmail())
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        memberRepo.save(member);
        String jwtToken = jwtService.generateToken(member);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Member member = (Member) memberRepo.findByEmail(request.getEmail()).orElseThrow();
//        List<Authority> authorities = authorityRepo.findAllById();

        var jwtToken = jwtService.generateToken(member);
        return AuthenticationResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .firstName(member.getName())
                .lastName(member.getFamilyName())
                .role(member.getRole())
                .token(jwtToken).build();
    }
    public void logout() {

        SecurityContextHolder.getContext().setAuthentication(null);
    }



    private void saveUserToken(Member user, String jwtToken) {
        var token = Token.builder()
                .member(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }

    private void revokeAllUserTokens(Member user) {
        var validUserTokens = tokenRepo.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUserName(refreshToken);
        if (userEmail != null) {
            var user = this.memberRepo.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .token(accessToken)
                        .firstName(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
