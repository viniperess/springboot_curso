package br.edu.ifsul.cstsi.curso.turma;

import br.edu.ifsul.cstsi.curso.curso.Curso;
import br.edu.ifsul.cstsi.curso.matricula.Matricula;
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
    @Column(name = "cod_turma", nullable = false)
    private Long cod_turma;
    @Basic
    @Column(name = "turno", nullable = true, length = 255)
    private String turno;
    @Basic
    @Column(name = "dt_ini", nullable = true)
    private Date dt_ini;
    @Basic
    @Column(name = "dt_fim", nullable = true)
    private Date dt_fim;
    @Basic
    @Column(name = "hr_ini", nullable = true)
    private Integer hr_ini;
    @Basic
    @Column(name = "hr_fim", nullable = true)
    private Integer hr_fim;
    @Basic
    @Column(name = "qtd_vagas", nullable = true)
    private Integer qtd_vagas;

    @OneToMany(mappedBy = "turmaByCodTurma")
    private Collection<Matricula> matriculasByCodTurma;

    @ManyToOne
    @JoinColumn(name = "cod_curso", referencedColumnName = "cod_curso", nullable = false)
    private Curso cursoByCodCurso;

    @Override
    public String toString() {
        return "\nTurma{" +
                "cod_turma=" + cod_turma +
                ", turno='" + turno + '\'' +
                ", dt_ini=" + dt_ini +
                ", dt_fim=" + dt_fim +
                ", hr_ini=" + hr_ini +
                ", hr_fim=" + hr_fim +
                ", qtd_vagas=" + qtd_vagas +
//                ", matriculasByCodTurma=" + matriculasByCodTurma +
                ", cursoByCodCurso=" + cursoByCodCurso +
                '}';
    }
}
