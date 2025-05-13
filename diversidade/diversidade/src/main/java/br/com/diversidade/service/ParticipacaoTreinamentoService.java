package br.com.diversidade.service;

import br.com.diversidade.model.ParticipacaoTreinamento;
import br.com.diversidade.repository.ParticipacaoTreinamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipacaoTreinamentoService {

    private final ParticipacaoTreinamentoRepository repository;

    public ParticipacaoTreinamentoService(ParticipacaoTreinamentoRepository repository) {
        this.repository = repository;
    }

    public ParticipacaoTreinamento salvar(ParticipacaoTreinamento pt) {
        return repository.save(pt);
    }

    public List<ParticipacaoTreinamento> listarPorColaborador(Long colaboradorId) {
        return repository.findByColaboradorId(colaboradorId);
    }

    public List<ParticipacaoTreinamento> listarTodos() {
        return repository.findAll();
    }
}
