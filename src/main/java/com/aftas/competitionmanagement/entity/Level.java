package com.aftas.competitionmanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity @ToString
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Table(name = "level")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private int code;
    private String description;
    private int points;

    @OneToMany(mappedBy = "level",cascade =  {CascadeType.MERGE, CascadeType.DETACH})
    @JsonManagedReference
    private Set<Fish> fish;
}
