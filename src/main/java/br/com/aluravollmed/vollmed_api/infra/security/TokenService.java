package br.com.aluravollmed.vollmed_api.infra.security;

import br.com.aluravollmed.vollmed_api.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario){

        try{
            var algorithmo = Algorithm.HMAC256("1322");
            return JWT.create()
                    .withIssuer("Voll med_api")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(DataExpiracao())
                    .sign(algorithmo);
        }catch (JWTCreationException exception){
            throw new RuntimeException("token não gerado");
        }

    }

     public String getSubject(String tokenJWT){
         try {
             var algoritmo = Algorithm.HMAC256("1322");
             return JWT.require(algoritmo)
                     .withIssuer("Voll med_api")
                     .build()
                     .verify(tokenJWT)
                     .getSubject();
         } catch (JWTVerificationException exception) {
             throw new RuntimeException("Token JWT inválido ou expirado!");
         }
     }




    private Instant DataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }


}
