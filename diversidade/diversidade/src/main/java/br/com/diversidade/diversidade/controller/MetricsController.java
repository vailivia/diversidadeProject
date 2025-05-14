package br.com.diversidade.diversidade.controller;

import br.com.diversidade.diversidade.model.Metrics;
import br.com.diversidade.diversidade.service.MetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
@Tag(name = "Metrics", description = "Training metrics management APIs")
public class MetricsController {

    private final MetricsService metricsService;

    @Autowired
    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping
    @Operation(summary = "Get all metrics", description = "Retrieves a list of all training metrics")
    public ResponseEntity<List<Metrics>> getAllMetrics() {
        return ResponseEntity.ok(metricsService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get metrics by ID", description = "Retrieves specific training metrics by ID")
    public ResponseEntity<Metrics> getMetricsById(
            @Parameter(description = "Metrics ID", required = true) @PathVariable Long id) {
        return metricsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new metrics", description = "Creates new training metrics")
    public ResponseEntity<Metrics> createMetrics(
            @Parameter(description = "Metrics object", required = true) @RequestBody Metrics metrics) {
        return ResponseEntity.ok(metricsService.save(metrics));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update metrics", description = "Updates existing training metrics")
    public ResponseEntity<Metrics> updateMetrics(
            @Parameter(description = "Metrics ID", required = true) @PathVariable Long id,
            @Parameter(description = "Updated metrics object", required = true) @RequestBody Metrics metrics) {
        return metricsService.findById(id)
                .map(existingMetrics -> {
                    metrics.setId(id);
                    return ResponseEntity.ok(metricsService.save(metrics));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete metrics", description = "Deletes training metrics by ID")
    public ResponseEntity<Void> deleteMetrics(
            @Parameter(description = "Metrics ID", required = true) @PathVariable Long id) {
        return metricsService.findById(id)
                .map(metrics -> {
                    metricsService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 