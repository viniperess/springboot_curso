package br.edu.ifsul.cstsi.curso.curso;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CursoRepository extends JpaRepository <Curso,Long>{
    @Query(value = "SELECT c FROM Curso c where c.nome like ?1")
    List<Curso> findByNome(String nome);

}

