package com.api.exerciseengine.controllers;

import com.api.exerciseengine.dto.AvaliacaoFisicaDTO;
import com.api.exerciseengine.entity.Aluno;
import com.api.exerciseengine.entity.AvaliacaoFisica;
import com.api.exerciseengine.repositories.AlunoRepository;
import com.api.exerciseengine.repositories.AvaliacaoFisicaRepository;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

  // TODO passar para um service

  private final AvaliacaoFisicaRepository avaliacaoFisicaRepository;
  private final AlunoRepository alunoRepository;

  @PostMapping("/registrar")
  public AvaliacaoFisica create(
    @RequestBody AvaliacaoFisicaDTO avaliacaoFisica
  ) {
    Aluno aluno = alunoRepository.findByCpf(avaliacaoFisica.alunoCpf()).get();

    AvaliacaoFisica avaliacao = AvaliacaoFisica
      .builder()
      .aluno(aluno)
      .peso(avaliacaoFisica.peso())
      .altura(avaliacaoFisica.altura())
      .build();

    return avaliacaoFisicaRepository.save(avaliacao);
  }

  @GetMapping
  public List<AvaliacaoFisica> getAll() {
    return avaliacaoFisicaRepository.findAll();
  }

  @GetMapping("/{id}")
  public AvaliacaoFisica getById(@PathVariable Long id) {
    AvaliacaoFisica avaliacaoFisica = avaliacaoFisicaRepository
      .findById(id)
      .get();
    return avaliacaoFisica;
  }

  @DeleteMapping("/{id}")
  @RolesAllowed("ADMIN")
  public void delete(@PathVariable Long id) {
    this.avaliacaoFisicaRepository.deleteById(id);
  }
}
