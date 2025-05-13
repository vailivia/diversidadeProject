package br.com.diversidade.repository;

import br.com.diversidade.model.Treinamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreinamentoRepository extends JpaRepository<Treinamento, Long> {
}
