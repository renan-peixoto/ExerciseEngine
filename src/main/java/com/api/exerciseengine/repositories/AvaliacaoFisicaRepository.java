package com.api.exerciseengine.repositories;

import com.api.exerciseengine.entity.AvaliacaoFisica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoFisicaRepository
  extends JpaRepository<AvaliacaoFisica, Long> {}
