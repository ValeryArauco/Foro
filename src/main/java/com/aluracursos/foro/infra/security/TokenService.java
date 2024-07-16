package com.aluracursos.foro.infra.security;

import com.aluracursos.foro.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API foro")
                    .withSubject(usuario.getCorreoElectronico())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar el  token jwt", exception);
        }
    }



    public String getSubject(String token) {
        if (token == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            verifier = JWT.require(algoritmo)
                    .withIssuer("API foro")
                    .build()
                    .verify(token);
            verifier.getSubject();

        } catch (JWTVerificationException exception) {
            //throw new RuntimeException("Token JWT inválido o expirado!");
            System.out.println(exception.toString());
        }
        if (verifier.getSubject()==null){
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }



    private Instant fechaExpiracion() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
