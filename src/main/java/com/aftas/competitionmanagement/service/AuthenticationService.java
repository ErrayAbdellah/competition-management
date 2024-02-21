package com.aftas.competitionmanagement.service;

import com.aftas.competitionmanagement.dto.request.AuthenticationRequest;
import com.aftas.competitionmanagement.dto.request.RegisterRequest;
import com.aftas.competitionmanagement.dto.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest user);

    AuthenticationResponse authenticate(AuthenticationRequest user);
    void logout();
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, IOException;
}
