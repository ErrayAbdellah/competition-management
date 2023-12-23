package com.aftas.competitionmanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Column(unique = true)
    private String name;
    private double averageWeight;

    @ManyToOne(cascade =  {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "level_id")
//    @JsonBackReference
    private Level level;

    @OneToMany(mappedBy = "fish",cascade =  {CascadeType.MERGE, CascadeType.DETACH})
    @JsonManagedReference
    private Set<Hunting> huntings ;
}
