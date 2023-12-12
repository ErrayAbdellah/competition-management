package com.aftas.competitionmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.swing.*;
import java.util.Set;

@Entity @ToString
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Table(name = "fish")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double averageWeight;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @OneToMany(mappedBy = "fish")
    private Set<Hunting> huntings ;
}
