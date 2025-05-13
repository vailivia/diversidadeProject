package br.com.diversidade.repository;

import br.com.diversidade.model.ParticipacaoTreinamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipacaoTreinamentoRepository extends JpaRepository<ParticipacaoTreinamento, Long> {
    List<ParticipacaoTreinamento> findByColaboradorId(Long colaboradorId);
}
