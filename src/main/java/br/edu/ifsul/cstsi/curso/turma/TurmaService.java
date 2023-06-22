package br.edu.ifsul.cstsi.curso.turma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private br.edu.ifsul.cstsi.curso.turma.TurmaRepository rep;


    public Turma insert(Turma turma) {
        Assert.isNull(turma.getCod_turma(), "Não foi possivel inserir o registro.");
        return rep.save(turma);
    }

    public List<Turma> getTurmas() {
        return rep.findAll();
    }

    public Turma getTurmaByCodTurma(Long codigo) {
        Optional<Turma> optional = rep.findById(codigo);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public Turma update(Turma turma) {
        Assert.notNull(turma.getCod_turma(), "Não foi possivel atualizar o registro.");
        Optional<Turma> optional = rep.findById(turma.getCod_turma());
        if(optional.isPresent()) {
            Turma db = optional.get();
            db.setTurno(turma.getTurno());
            db.setDt_ini(turma.getDt_ini());
            db.setDt_fim(turma.getDt_fim());
            db.setHr_ini(turma.getHr_ini());
            db.setHr_fim(turma.getHr_fim());
            db.setQtd_vagas(turma.getQtd_vagas());
            db.setCursoByCodCurso(turma.getCursoByCodCurso());
            rep.save(db);
            return db;
        } else {
            return null;
        }
    }

    public void delete(Long cod_turma) {
        rep.deleteById(cod_turma);
    }

    public List<Turma> getTurmasByTurno(String turno) {
        return new ArrayList<>(rep.findByTurno(turno + "%"));
    }
}
