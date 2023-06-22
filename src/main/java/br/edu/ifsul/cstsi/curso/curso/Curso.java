package br.edu.ifsul.cstsi.curso.curso;

import br.edu.ifsul.cstsi.curso.turma.Turma;
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
    @Column(name = "cod_curso", nullable = false)
    private Long cod_curso;
    @Basic
    @Column(name = "nome", nullable = true, length = 255)
    private String nome;
    @Basic
    @Column(name = "pre_requisito", nullable = true, length = 255)
    private String pre_requisito;
    @Basic
    @Column(name = "carga_hora", nullable = true)
    private Integer carga_hora;
    @Basic
    @Column(name = "valor", nullable = true, precision = 0)
    private Double valor;
    @OneToMany(mappedBy = "cursoByCodCurso")
    private Collection<Turma> turmasByCodCurso;

    @Override
    public String toString() {
        return "\nCurso{" +
                "codCurso=" + cod_curso +
                ", nome='" + nome + '\'' +
                ", preRequisito='" + pre_requisito + '\'' +
                ", cargaHora=" + carga_hora +
                ", valor=" + valor +
                '}';
    }
}
