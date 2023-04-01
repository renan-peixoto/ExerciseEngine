package com.api.exerciseengine.security.config;

import com.api.exerciseengine.repositories.UsuarioRepository;
import com.api.exerciseengine.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class FilterToken extends OncePerRequestFilter {

  private final TokenService tokenService;
  private final UsuarioRepository usuarioRepository;

  @Override
  protected void doFilterInternal(
    HttpServletRequest request,
    HttpServletResponse response,
    FilterChain filterChain
  ) throws ServletException, IOException {
    String token;

    var authHeader = request.getHeader("Authorization");

    if (authHeader != null) {
      token = authHeader.replace("Bearer ", "");
      var subject = this.tokenService.getSubject(token);

      var usuario = this.usuarioRepository.findByEmail(subject);

      var authentication = new UsernamePasswordAuthenticationToken(
        usuario,
        null,
        usuario.get().getAuthorities()
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
