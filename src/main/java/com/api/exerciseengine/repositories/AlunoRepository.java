package com.api.exerciseengine.repositories;

import com.api.exerciseengine.entity.Aluno;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
  Optional<Aluno> findByCpf(String cpf);
}
