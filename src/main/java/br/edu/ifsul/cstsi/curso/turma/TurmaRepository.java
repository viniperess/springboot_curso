package br.edu.ifsul.cstsi.curso.turma;

import br.edu.ifsul.cstsi.curso.curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma,Long> {
    @Query(value = "SELECT c FROM Turma c where c.turno like ?1")
    List<Turma> findByTurno(String turno);
}
