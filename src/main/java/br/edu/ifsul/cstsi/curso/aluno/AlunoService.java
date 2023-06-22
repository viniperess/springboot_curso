package br.edu.ifsul.cstsi.curso.aluno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository rep;

    public List<Aluno> getAlunos() {
        return rep.findAll();
    }

    public Aluno getAlunoById(Long id) {
        Optional<Aluno> optional = rep.findById(id);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
    public Aluno getAlunoByCpf(String cpf) {
        Optional<Aluno> optional = rep.findByCpf(cpf);
        if(optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    public List<Aluno> getAlunosByNome(String nome) {
        return new ArrayList<>(rep.findByNome(nome + "%"));
    }

    public Aluno insert(Aluno aluno) {
        Assert.isNull(aluno.getCod_aluno(),"Não foi possível inserir o registro");
        return rep.save(aluno);
    }

    public Aluno update(Aluno aluno) {
        Assert.notNull(aluno.getCod_aluno(),"Não foi possível atualizar o registro");

        // Busca o produto no banco de dados
        Optional<Aluno> optional = rep.findById(aluno.getCod_aluno());
        if(optional.isPresent()) {
            Aluno db = optional.get();
            // Copiar as propriedades

            db.setNome(aluno.getNome());
            db.setCpf(aluno.getCpf());
            db.setRg(aluno.getRg());
            db.setFone(aluno.getFone());
            db.setEndereco(aluno.getEndereco());
            // Atualiza o produto
            rep.save(db);

            return db;
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}