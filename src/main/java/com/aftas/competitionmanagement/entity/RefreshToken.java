//package com.aftas.competitionmanagement.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.Instant;
//
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Getter
//@Setter
//public class RefreshToken {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//    @Column(nullable = false, unique = true)
//    private String refreshToken;
//    @Column(nullable = false)
//    private Instant expiryDate;
//    private boolean revoked;
//    @ManyToOne
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
//    private Member member;
//}
