package br.com.diversidade.diversidade;


import br.com.diversidade.diversidade.Colaborador;
import br.com.diversidade.diversidade.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    public List<Colaborador> listar() {
        return repository.findAll();
    }

    public Colaborador salvar(Colaborador colaborador) {
        return repository.save(colaborador);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Colaborador atualizar(Long id, Colaborador colaborador) {
        colaborador.setId(id);
        return repository.save(colaborador);
    }
}

