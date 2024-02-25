package com.aftas.competitionmanagement.dto.response;


import com.aftas.competitionmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String token;

    private String refreshToken;
//    @Enumerated(EnumType.STRING)
    private Role role;
}
