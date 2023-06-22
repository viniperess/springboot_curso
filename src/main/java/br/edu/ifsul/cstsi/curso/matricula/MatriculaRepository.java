package br.edu.ifsul.cstsi.curso.matricula;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula,Long> {
    @Query(value = "SELECT m FROM Matricula m where m.forma_pagto like ?1")
    List<Matricula> findByForma(String forma);

}
