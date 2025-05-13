package br.com.diversidade.service;

import br.com.diversidade.model.Colaborador;
import br.com.diversidade.model.Treinamento;
import br.com.diversidade.repository.ColaboradorRepository;
import br.com.diversidade.repository.TreinamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreinamentoService {

    private final TreinamentoRepository repository;
    private final ColaboradorRepository colaboradorRepository;

    public TreinamentoService(
            TreinamentoRepository repository,
            ColaboradorRepository colaboradorRepository
    ) {
        this.repository = repository;
        this.colaboradorRepository = colaboradorRepository;
    }


    public Treinamento save(Treinamento t) {
        return repository.save(t);
    }

    public List<Treinamento> findAll() {
        return repository.findAll();
    }

    public boolean associarColaborador(Long treinamentoId, Long colaboradorId) {
        Optional<Treinamento> optionalTreinamento = repository.findById(treinamentoId);
        Optional<Colaborador> optionalColaborador = colaboradorRepository.findById(colaboradorId);

        if (optionalTreinamento.isPresent() && optionalColaborador.isPresent()) {
            Treinamento treinamento = optionalTreinamento.get();
            Colaborador colaborador = optionalColaborador.get();

            treinamento.getColaboradores().add(colaborador);
            repository.save(treinamento);
            return true;
        }
        return false;
    }

    public Optional<Treinamento> findById(Long id) {
        return repository.findById(id);
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
