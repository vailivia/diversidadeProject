package br.com.diversidade.diversidade.service;

import br.com.diversidade.diversidade.model.Training;
import br.com.diversidade.diversidade.repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Transactional(readOnly = true)
    public List<Training> findAll() {
        return trainingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Training> findById(Long id) {
        return trainingRepository.findById(id);
    }

    @Transactional
    public Training save(Training training) {
        return trainingRepository.save(training);
    }

    @Transactional
    public void deleteById(Long id) {
        trainingRepository.deleteById(id);
    }
} 