package com.api.exerciseengine.services;

import com.api.exerciseengine.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

  // private final AuthenticationManager authenticationManager;
  private final UsuarioRepository usuarioRepository;

  // private final TokenService tokenService;

  // public String login(Login login) {
  //   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
  //     login.email(),
  //     login.senha()
  //   );

  //   Authentication authenticate =
  //     this.authenticationManager.authenticate(
  //         usernamePasswordAuthenticationToken
  //       );

  //   var usuario = (Usuario) authenticate.getPrincipal();

  //   return tokenService.gerarToken(usuario);
  // }

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    return usuarioRepository.findByEmail(username).get();
  }
  // public Usuario registrar(Usuario usuario) {
  //   Usuario usuarioCadastrado = Usuario
  //     .builder()
  //     .email(usuario.getEmail())
  //     .senha(usuario.getSenha())
  //     .build();

  //   return usuarioRepository.save(usuarioCadastrado);
  // }
}
