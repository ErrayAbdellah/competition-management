package com.aftas.competitionmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(mappedBy = "authorities",cascade =  {CascadeType.MERGE, CascadeType.DETACH})
    private Set<Role> roles ;

}
