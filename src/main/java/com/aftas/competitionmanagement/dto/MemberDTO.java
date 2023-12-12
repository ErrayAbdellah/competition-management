package com.aftas.competitionmanagement.dto;

import com.aftas.competitionmanagement.enums.IdentityDocumentTyp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.util.Date;
@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class MemberDTO {
    private long id;
    @Positive(message = "Num must be a positive number")
    private int num;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Family name cannot be blank")
    private String familyName;

    @Past(message = "Accession date must be in the past")
    private Date accessionDate;

    @NotBlank(message = "Nationality cannot be blank")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null")
    private IdentityDocumentTyp identityDocument;

    @NotBlank(message = "Identity number cannot be blank")
    private String identityNumber;
}
