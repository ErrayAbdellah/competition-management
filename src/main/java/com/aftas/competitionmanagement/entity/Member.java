package com.aftas.competitionmanagement.entity;

import com.aftas.competitionmanagement.enums.IdentityDocumentTyp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity @ToString
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @Builder
@Table(name = "member")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int num;
    private String name;
    private String familyName;
    private Date accessionDate;
    private String nationality;
    private String email;
    private String password;
    private IdentityDocumentTyp identityDocument;
    @Column(unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    private List<Token> tokens;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private Set<Ranking> rankings;

    @OneToMany(mappedBy = "member",cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Set<Hunting> huntings ;

    @ManyToOne(cascade =  {CascadeType.MERGE, CascadeType.DETACH},fetch = FetchType.EAGER)
    private Role role ;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.getName()));
//    }
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = role.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());

    // Include the role name itself as an authority
    authorities.add(new SimpleGrantedAuthority(role.getName()));

    return authorities;
}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
