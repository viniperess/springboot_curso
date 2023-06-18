package br.edu.ifsul.cstsi.curso.Matricula;

import br.edu.ifsul.cstsi.curso.Aluno.Aluno;
import br.edu.ifsul.cstsi.curso.Turma.Turma;
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
    @Column(name = "codMatricula", nullable = false)
    private Integer codMatricula;
    @Basic
    @Column(name = "data", nullable = true)
    private Date data;
    @Basic
    @Column(name = "formaPagto", nullable = true)
    private Integer formaPagto;
    @Basic
    @Column(name = "numMat", nullable = true)
    private Integer numMat;
//    @Basic
//    @Column(name = "codAluno", nullable = false)
//    private Integer codAluno;
//    @Basic
//    @Column(name = "codTurma", nullable = false)
//    private Integer codTurma;
    @ManyToOne
    @JoinColumn(name = "codAluno", referencedColumnName = "codAluno", nullable = false)
    private Aluno alunoByCodAluno;
    @ManyToOne
    @JoinColumn(name = "codTurma", referencedColumnName = "codTurma", nullable = false)
    private Turma turmaByCodTurma;


}
