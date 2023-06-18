package br.edu.ifsul.cstsi.curso.Turma;

import br.edu.ifsul.cstsi.curso.Curso.Curso;
import br.edu.ifsul.cstsi.curso.Matricula.Matricula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turma {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codTurma", nullable = false)
    private Integer codTurma;
    @Basic
    @Column(name = "turno", nullable = true, length = 255)
    private String turno;
    @Basic
    @Column(name = "dtIni", nullable = true)
    private Date dtIni;
    @Basic
    @Column(name = "dtFim", nullable = true)
    private Date dtFim;
    @Basic
    @Column(name = "hrIni", nullable = true)
    private Integer hrIni;
    @Basic
    @Column(name = "hrFim", nullable = true)
    private Integer hrFim;
    @Basic
    @Column(name = "qtdVagas", nullable = true)
    private Integer qtdVagas;
//    @Basic
//    @Column(name = "codCurso", nullable = false)
//    private Integer codCurso;
    @OneToMany(mappedBy = "turmaByCodTurma")
    private Collection<Matricula> matriculasByCodTurma;
    @ManyToOne
    @JoinColumn(name = "codCurso", referencedColumnName = "codCurso", nullable = false)
    private Curso cursoByCodCurso;


}
