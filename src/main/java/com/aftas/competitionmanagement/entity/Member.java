package com.aftas.competitionmanagement.entity;

import com.aftas.competitionmanagement.enums.IdentityDocumentTyp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity @ToString
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    private IdentityDocumentTyp identityDocument;
    @Column(unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private Set<Ranking> rankings;

    @OneToMany(mappedBy = "member",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Hunting> huntings ;
}
