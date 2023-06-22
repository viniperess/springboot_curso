package br.edu.ifsul.cstsi.curso.matricula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private br.edu.ifsul.cstsi.curso.matricula.MatriculaRepository rep;

    public Matricula insert(Matricula matricula) {
        Assert.isNull(matricula.getCod_matricula(), "Não foi possivel inserir.");
        return rep.save(matricula);
    }

    public List<Matricula> getMatriculas() {
        return rep.findAll();
    }

    public Matricula getMatriculaByCodMatricula(Long codigo) {
        Optional<Matricula> optional = rep.findById(codigo);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public Matricula update(Matricula matricula) {
        Assert.notNull(matricula.getCod_matricula(), "Não foi possivel atualizar.");
        Optional<Matricula> optional = rep.findById(matricula.getCod_matricula());
        if(optional.isPresent()){
            Matricula db = optional.get();
            db.setNum_mat(matricula.getNum_mat());
            db.setData(matricula.getData());
            db.setForma_pagto(matricula.getForma_pagto());
            db.setTurmaByCodTurma(matricula.getTurmaByCodTurma());
            db.setAlunoByCodAluno(matricula.getAlunoByCodAluno());
            rep.save(db);
            return db;
        } else {
            return null;
        }
    }

    public void delete(Long cod_matricula) {
        rep.deleteById(cod_matricula);
    }

    public List<Matricula> getMatriculasByFormaPg(String forma) {
        return new ArrayList<>(rep.findByForma(forma + "%"));
    }
}
