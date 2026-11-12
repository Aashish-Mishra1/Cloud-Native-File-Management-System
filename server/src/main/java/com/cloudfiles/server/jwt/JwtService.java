package com.cloudfiles.server.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cloudfiles.server.utils.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.function.BiFunction;

import static com.cloudfiles.server.utils.TokenType.ACCESS;
import static com.cloudfiles.server.utils.TokenType.REFRESH;

@Component
public class JwtService {

    @Value("${jwt.token.issuer}")
    private String issuer;

    @Value("${jwt.token.subject}")
    private String subject;

    @Value("${jwt.token.secret}")
    private String SECRET;

    public String validateToken(final String token, TokenType type) {
        JWTVerifier verifier = JWT
                .require(getAlgorithm.apply(type, SECRET))
                .withSubject("email")
                .withIssuer(issuer)
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }

    public String generateToken(String email, TokenType type) {
        var date = Date.from(ZonedDateTime.now().plusMinutes(type.getExpiration()).toInstant());
        return JWT.create()
                .withClaim("email", email)
                .withSubject(subject)
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withExpiresAt(date)
                .sign(getAlgorithm.apply(type, SECRET));
    }

    private final BiFunction<TokenType, String, Algorithm> getAlgorithm = (type, secret) ->
            type == ACCESS ? Algorithm.HMAC256(secret) :
                    type == REFRESH ? Algorithm.HMAC512(secret) :
                            Algorithm.HMAC384(secret);
}
