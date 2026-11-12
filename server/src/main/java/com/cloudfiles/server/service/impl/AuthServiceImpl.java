package com.cloudfiles.server.service.impl;

import com.cloudfiles.server.jwt.JwtService;
import com.cloudfiles.server.payload.AuthRequest;
import com.cloudfiles.server.payload.RegistrationRequest;
import com.cloudfiles.server.payload.TokenResponse;
import com.cloudfiles.server.service.AuthService;
import com.cloudfiles.server.service.Base64Service;
import com.cloudfiles.server.utils.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.cloudfiles.server.utils.AppConstants.ACCESS_TOKEN_EXPIRATION;
import static com.cloudfiles.server.utils.AppConstants.REFRESH_TOKEN_EXPIRATION;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final Base64Service base64Service;

    public TokenResponse authenticate(AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password())
        );
        if (authenticate.isAuthenticated()) {
            return createResponse(authRequest.email());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    public String validateToken(String token, TokenType type) {
        return jwtService.validateToken(token, type);
    }

    public TokenResponse createResponse(String email) {
        return TokenResponse.builder()
                .accessTokenExpiration(ACCESS_TOKEN_EXPIRATION)
                .refreshTokenExpiration(REFRESH_TOKEN_EXPIRATION)
                .accessToken(jwtService.generateToken(email, TokenType.ACCESS))
                .refreshToken(jwtService.generateToken(email, TokenType.REFRESH))
                .build();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @SneakyThrows
    public String encodeUserData(RegistrationRequest request) {
        return base64Service.encodeUserData(request);
    }

    public RegistrationRequest decodeUserDate(String data) {
        return base64Service.decodeUserData(data);
    }

    public String generateToken(String email, TokenType tokenType) {
        return jwtService.generateToken(email, tokenType);
    }
}
