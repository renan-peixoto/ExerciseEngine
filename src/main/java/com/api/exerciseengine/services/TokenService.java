package com.api.exerciseengine.services;

import com.api.exerciseengine.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private static final String SECRET_KEY =
    "566B58703273357638792F423F4528482B4D6251655468576D5A713374367739";

  public String gerarToken(Usuario usuario) {
    return JWT
      .create()
      // .withIssuer("AvaliacaoFisica")
      .withSubject(usuario.getUsername())
      .withClaim("id", usuario.getId())
      .withExpiresAt(
        LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.of("-03:00"))
      )
      .sign(Algorithm.HMAC256(SECRET_KEY));
  }

  public String getSubject(String token) {
    return JWT
      .require(Algorithm.HMAC256(SECRET_KEY))
      // .withIssuer("AvaliacaoFisica")
      .build()
      .verify(token)
      .getSubject();
  }
}
