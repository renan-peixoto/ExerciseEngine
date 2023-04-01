package com.api.exerciseengine.controllers;

import com.api.exerciseengine.dto.Login;
import com.api.exerciseengine.entity.Roles;
import com.api.exerciseengine.entity.Usuario;
import com.api.exerciseengine.repositories.UsuarioRepository;
import com.api.exerciseengine.services.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  // TODO passar para um service (RegistrarUsuarioService)

  // private final AuthService service;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  private final UsuarioRepository usuarioRepository;
  private final TokenService tokenService;

  @PostMapping("/registrar")
  public Usuario legistrar(@RequestBody Usuario usuario) {
    Usuario usuarioCadastrado = Usuario
      .builder()
      .email(usuario.getEmail())
      .password(passwordEncoder.encode(usuario.getPassword()))
      .nome(usuario.getNome())
      .role(Roles.USER)
      .build();

    return usuarioRepository.save(usuarioCadastrado);
  }

  @PostMapping("/login")
  public String login(@RequestBody Login login) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
      login.email(),
      login.password()
    );

    Authentication authenticate =
      this.authenticationManager.authenticate(
          usernamePasswordAuthenticationToken
        );

    var usuario = (Usuario) authenticate.getPrincipal();

    return tokenService.gerarToken(usuario);
  }
}
