package com.api.exerciseengine.controllers;

import com.api.exerciseengine.dto.AlunoDto;
import com.api.exerciseengine.entity.Aluno;
import com.api.exerciseengine.repositories.AlunoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/aluno")
public class AlunoController {

  // TODO passar para um service

  private final AlunoRepository alunoRepository;

  @PostMapping(path = "/criarAluno")
  public ResponseEntity<Aluno> criarAluno(@RequestBody AlunoDto alunoDTO) {
    Aluno aluno = Aluno
      .builder()
      .nome(alunoDTO.nome())
      .sobrenome(alunoDTO.sobrenome())
      .cpf(alunoDTO.cpf())
      .bairro(alunoDTO.bairro())
      .dataDeNascimeto(alunoDTO.dataDeNacimento())
      .build();
    Aluno alunoSalvo = alunoRepository.save(aluno);
    return ResponseEntity.ok(alunoSalvo);
  }

  @GetMapping
  public List<Aluno> getAll() {
    return alunoRepository.findAll();
  }
}
