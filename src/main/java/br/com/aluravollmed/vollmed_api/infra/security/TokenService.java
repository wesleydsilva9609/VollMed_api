package br.com.aluravollmed.vollmed_api.infra.security;

import br.com.aluravollmed.vollmed_api.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(DataInspiracao())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new  RuntimeException("Token não gerado");
        }
    }

    public String getSubject(String tokenJWT){

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("API Voll.med")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT)
                    .getSubject();


        } catch (JWTVerificationException exception){
            throw new RuntimeException("token Invalido");
        }
    }

    private Instant DataInspiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
