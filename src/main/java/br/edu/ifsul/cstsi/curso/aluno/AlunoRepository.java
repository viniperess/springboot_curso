package br.edu.ifsul.cstsi.curso.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno,Long>{
    @Query(value = "SELECT a FROM Aluno a where a.nome like ?1")
    List<Aluno> findByNome(String nome);
    @Query(value = "SELECT a FROM Aluno a where a.cpf = ?1 ")
    Optional<Aluno> findByCpf(String cpf);

}

