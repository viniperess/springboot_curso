package br.edu.ifsul.cstsi.curso.curso;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private br.edu.ifsul.cstsi.curso.curso.CursoRepository rep;

    public List<Curso> getCursos(){ return rep.findAll(); }

    public Curso getCursoById(Long codigo) {
        Optional<Curso> optional = rep.findById(codigo);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }

    public Curso insert(Curso curso) {
        System.out.println(curso);
        Assert.isNull(curso.getCod_curso(), "Não foi possivel inserir o registro.");
        return rep.save(curso);
    }

    public Curso update(Curso curso) {
        Assert.notNull(curso.getCod_curso(), "Não foi possivel atualizar o registro.");

        Optional<Curso> optional = rep.findById(curso.getCod_curso());
        if(optional.isPresent()) {
            Curso db = optional.get();
            db.setNome(curso.getNome());
            db.setPre_requisito(curso.getPre_requisito());
            db.setValor(curso.getValor());
            db.setCarga_hora(curso.getCarga_hora());
            db.setTurmasByCodCurso(curso.getTurmasByCodCurso());
            rep.save(db);
            return db;
        } else {
            return null;
        }
    }

    public void delete(Long codCurso) {
        rep.deleteById(codCurso);
    }

    public List<Curso> getCursosByNome(String nome) {
        return new ArrayList<>(rep.findByNome(nome + "%"));
    }
}
