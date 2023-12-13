package com.aftas.competitionmanagement.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private String statusCode;
    private Date time;
    private List<String> details;

    public ErrorResponse(String message, String statusCode, List<String> details) {
        this.message = message;
        this.statusCode = statusCode;
        this.details = details;
        this.time = new Date();
    }
}
