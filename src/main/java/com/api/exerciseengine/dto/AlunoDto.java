package com.api.exerciseengine.dto;

import java.time.LocalDate;

// TODO verificar se da pra colocar anotações de verificação em records
public record AlunoDto(
  String nome,
  String sobrenome,
  String cpf,
  String bairro,
  LocalDate dataDeNacimento
) {}
