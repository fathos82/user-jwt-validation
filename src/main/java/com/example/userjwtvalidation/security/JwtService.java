package com.example.userjwtvalidation.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtService {
    private String SECRET_KEY = "fantasticaChaveDeValidacaoAestheticMil";
    private String ISSUER = "user_validation_jwt_aesthetic";
    public String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return  JWT.create()
                    .withSubject(username)
                    .withIssuer(ISSUER)
                    .withIssuedAt(creationDate())
                    .withExpiresAt(generateExpiresDate())
                    .sign(algorithm);
        } catch (JWTCreationException e){
            throw new RuntimeException("Error to create token");
        }


    }
    public String validateTokenAndReturnSubject(String token) {
        try {
            // Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER) // Define o emissor do token
                    .build()
                    .verify(token) // Verifica a validade do token
                    .getSubject(); // Obtém o assunto (neste caso, o nome de usuário) do token
        } catch (JWTVerificationException exception){
           return null;
        }
    }
    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

    private Instant generateExpiresDate() {
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo"); // consi
        ZonedDateTime now = ZonedDateTime.now(zoneId); // pega o
        return now.plusHours(3).toInstant(); // define que ira e
    }
}
