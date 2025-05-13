package br.com.diversidade.service;
import br.com.diversidade.model.Colaborador;
import br.com.diversidade.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    public Colaborador salvar(Colaborador colaborador) {
        return repository.save(colaborador);
    }

    public List<Colaborador> listar() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
