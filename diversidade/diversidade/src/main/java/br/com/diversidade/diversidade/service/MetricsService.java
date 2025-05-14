package br.com.diversidade.diversidade.service;

import br.com.diversidade.diversidade.model.Metrics;
import br.com.diversidade.diversidade.repository.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MetricsService {

    private final MetricsRepository metricsRepository;

    @Autowired
    public MetricsService(MetricsRepository metricsRepository) {
        this.metricsRepository = metricsRepository;
    }

    @Transactional(readOnly = true)
    public List<Metrics> findAll() {
        return metricsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Metrics> findById(Long id) {
        return metricsRepository.findById(id);
    }

    @Transactional
    public Metrics save(Metrics metrics) {
        return metricsRepository.save(metrics);
    }

    @Transactional
    public void deleteById(Long id) {
        metricsRepository.deleteById(id);
    }
} 