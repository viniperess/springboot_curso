package br.edu.ifsul.cstsi.curso.matricula;

import br.edu.ifsul.cstsi.curso.aluno.Aluno;
import br.edu.ifsul.cstsi.curso.turma.Turma;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Matricula {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cod_matricula", nullable = false)
    private Long cod_matricula;
    @Basic
    @Column(name = "data", nullable = true)
    private Date data;
    @Basic
    @Column(name = "forma_pagto", nullable = true)
    private String forma_pagto;
    @Basic
    @Column(name = "num_mat", nullable = true)
    private Integer num_mat;
//    @Basic
//    @Column(name = "codAluno", nullable = false)
//    private Integer codAluno;
//    @Basic
//    @Column(name = "codTurma", nullable = false)
//    private Integer codTurma;
    @ManyToOne
    @JoinColumn(name = "cod_aluno", referencedColumnName = "cod_aluno", nullable = false)
    private Aluno alunoByCodAluno;
    @ManyToOne
    @JoinColumn(name = "cod_turma", referencedColumnName = "cod_turma", nullable = false)
    private Turma turmaByCodTurma;

    @Override
    public String toString() {
        return "\nMatricula{" +
                "codMatricula=" + cod_matricula +
                ", data=" + data +
                ", formaPagto=" + forma_pagto +
                ", numMat=" + num_mat +
                ", alunoByCodAluno=" + alunoByCodAluno +
                ", turmaByCodTurma=" + turmaByCodTurma +
                '}';
    }
}
