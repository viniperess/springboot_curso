package br.edu.ifsul.cstsi.curso.Aluno;

import br.edu.ifsul.cstsi.curso.Matricula.Matricula;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Aluno {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "codAluno", nullable = false)
    private Integer codAluno;
    @Basic
    @Column(name = "cpf", nullable = true, length = 255)
    private String cpf;
    @Basic
    @Column(name = "rg", nullable = true, length = 255)
    private String rg;
    @Basic
    @Column(name = "nome", nullable = true, length = 255)
    private String nome;
    @Basic
    @Column(name = "fone", nullable = true, length = 255)
    private String fone;
    @Basic
    @Column(name = "endereco", nullable = true, length = 255)
    private String endereco;
    @OneToMany(mappedBy = "alunoByCodAluno")
    private Collection<Matricula> matriculasByCodAluno;

    @Override
    public String toString() {
        return "\nAluno{" +
                "codAluno=" + codAluno +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", nome='" + nome + '\'' +
                ", fone='" + fone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", matriculasByCodAluno=" + matriculasByCodAluno +
                '}';
    }
}
