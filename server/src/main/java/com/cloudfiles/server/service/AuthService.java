package com.cloudfiles.server.service;

import com.cloudfiles.server.payload.AuthRequest;
import com.cloudfiles.server.payload.RegistrationRequest;
import com.cloudfiles.server.payload.TokenResponse;
import com.cloudfiles.server.utils.TokenType;

public interface AuthService {
    TokenResponse authenticate(AuthRequest authRequest);
    String validateToken(String token, TokenType type);
    TokenResponse createResponse(String email);
    String encodePassword(String password);
    String encodeUserData(RegistrationRequest request);
    RegistrationRequest decodeUserDate(String data);
    String generateToken(String email, TokenType tokenType);
}
