package com.aftas.competitionmanagement.dto.response;

import com.aftas.competitionmanagement.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private String name ;
    private List<Authority> authorities;
}
