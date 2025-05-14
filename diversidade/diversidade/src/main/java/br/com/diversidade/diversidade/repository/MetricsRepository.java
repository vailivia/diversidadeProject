package br.com.diversidade.diversidade.repository;

import br.com.diversidade.diversidade.model.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics, Long> {
} 