//package com.aftas.competitionmanagement.service.jwt;
//
//import com.aftas.competitionmanagement.entity.Member;
//import com.aftas.competitionmanagement.entity.RefreshToken;
//import com.aftas.competitionmanagement.entity.Role;
//import com.aftas.competitionmanagement.repository.IMemberRepo;
//import com.aftas.competitionmanagement.repository.IRefreshTokenRepo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.time.OffsetDateTime;
//import java.time.ZoneOffset;
//import java.util.*;
//
//@Service
//@RequiredArgsConstructor
//public class RefreshTokenService {
//    private final IRefreshTokenRepo refreshTokenRepository;
//    private final IMemberRepo memberRepo;
//    private final JwtService jwtService;
//
//
//
//    public Map<String, String> generateAccessAndRefreshToken(Authentication authentication,
//                                                             List<Role> roles) {
//        String subject = authentication.getName();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Instant instant = Instant.now();
//        Map<String, String> token = new HashMap<>();
//        token.put("access_Token", jwtService.jwtAccessTokenEncoded(subject, instant, authorities, roles));
//        token.put("refresh_Token", this.jwtRefreshTokenEncoded(subject, instant));
//        return token;
//    }
//
//
//    public String jwtRefreshTokenEncoded(String subject, Instant instant){
//        Member member = (Member) memberRepo.findByEmail(subject)
//                .orElseThrow(() ->  new RuntimeException("resources not found"));
//        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
//        OffsetDateTime plusOneYear = offsetDateTime.plusYears(1);
//        Instant result = plusOneYear.toInstant();
//        RefreshToken refreshToken = new RefreshToken()
//                .builder()
//                .refreshToken(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()))
//                .expiryDate(result)
//                .revoked(false)
//                .member(member)
//                .build();
//        RefreshToken refreshTokenSaved = this.refreshTokenRepository.save(refreshToken);
//        return refreshTokenSaved.getRefreshToken();
//
//    }
//
//    public Map<String,String> generateAccessTokenByRefreshToken(String refreshToken){
//        Member member = refreshTokenRepository.findByRefreshToken(refreshToken)
//                .map(this::verifyExpiration)
//                .map(this::verifyIsRevoked)
//                .orElseThrow(()-> new RuntimeException("refresh token not found"))
//                .getMember();
//        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
//        String accessToken = jwtService.jwtAccessTokenEncoded(member.getUsername(), Instant.now(),
//                authentication.getAuthorities(),member.getRole().stream().toList());
//        Map<String, String> token = new HashMap<>();
//        token.put("access_Token", accessToken);
//        token.put("refresh_Token", refreshToken);
//        return token;
//    }
//
//    public RefreshToken verifyExpiration(RefreshToken refreshToken){
//        if(refreshToken == null){
//            throw new TokenException(null, "Token is null");
//        }
//        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
//            throw new TokenException(refreshToken.getRefreshToken(), "Refresh token was expired. Please make a new authentication request");
//        }
//        return refreshToken;
//    }
//
//    public RefreshToken verifyIsRevoked(RefreshToken refreshToken){
//        if(refreshToken.isRevoked()){
//            throw new TokenException(refreshToken.getRefreshToken(), "Refresh token was expired. Please make a new authentication request");
//        }
//        return refreshToken;
//    }
//}
