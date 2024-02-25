package com.aftas.competitionmanagement.dto.response;

import com.aftas.competitionmanagement.enums.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO {
    private String token;
    private TokenType tokenType;
}
