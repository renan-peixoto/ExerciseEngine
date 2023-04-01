package com.api.exerciseengine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "_aluno")
@JsonIgnoreProperties({ "hibernateLazyInitializar", "handler" })
@Entity
public class Aluno {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  // @OneToOne
  // @JoinColumn(name = "usuario_id")
  // private Usuario usuario;

  private String nome;

  private String sobrenome;

  @Column(unique = true)
  private String cpf;

  private String bairro;

  @DateTimeFormat(pattern = "dd/MM/yyyy")
  private LocalDate dataDeNascimeto; // TODO procurar método para mudar formato

  @JsonIgnore
  @Builder.Default
  @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
  private List<AvaliacaoFisica> avaliacaoFisica = new ArrayList<>();
}
