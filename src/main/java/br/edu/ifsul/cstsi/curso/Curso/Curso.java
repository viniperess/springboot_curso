package br.edu.ifsul.cstsi.curso.Curso;

import br.edu.ifsul.cstsi.curso.Turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Curso {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codCurso", nullable = false)
    private Integer codCurso;
    @Basic
    @Column(name = "nome", nullable = true, length = 255)
    private String nome;
    @Basic
    @Column(name = "preRequisito", nullable = true, length = 255)
    private String preRequisito;
    @Basic
    @Column(name = "cargaHora", nullable = true)
    private Integer cargaHora;
    @Basic
    @Column(name = "valor", nullable = true, precision = 0)
    private Double valor;
    @OneToMany(mappedBy = "cursoByCodCurso")
    private Collection<Turma> turmasByCodCurso;

  }
