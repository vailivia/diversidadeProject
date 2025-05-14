package br.com.diversidade.diversidade.repository;

import br.com.diversidade.diversidade.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingRepository extends JpaRepository<Training, Long> {
} 