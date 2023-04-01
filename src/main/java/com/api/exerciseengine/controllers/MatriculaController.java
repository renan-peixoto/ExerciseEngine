package com.api.exerciseengine.controllers;

import com.api.exerciseengine.dto.MatriculaDTO;
import com.api.exerciseengine.entity.Aluno;
import com.api.exerciseengine.entity.Matricula;
import com.api.exerciseengine.entity.Usuario;
import com.api.exerciseengine.repositories.AlunoRepository;
import com.api.exerciseengine.repositories.MatriculaRepository;
import com.api.exerciseengine.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matriculas")
public class MatriculaController {

  // TODO passar para um service

  private final UsuarioRepository usuarioRepository;
  private final AlunoRepository alunoRepository;
  private final MatriculaRepository matriculaRepository;

  @PostMapping
  public ResponseEntity<Matricula> criarAluno(
    @RequestBody MatriculaDTO matriculaDto
  ) {
    Usuario usuarioId = usuarioRepository
      .findByEmail(matriculaDto.usuarioEmail())
      .get();
    Aluno alunoId = alunoRepository.findByCpf(matriculaDto.alunoCpf()).get();
    Matricula matricula = Matricula
      .builder()
      .aluno(alunoId)
      .usuario(usuarioId)
      .build();
    Matricula matriculaSalva = matriculaRepository.save(matricula);
    return ResponseEntity.ok(matriculaSalva);
  }
}
