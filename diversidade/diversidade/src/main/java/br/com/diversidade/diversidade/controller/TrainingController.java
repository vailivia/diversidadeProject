package br.com.diversidade.diversidade.controller;

import br.com.diversidade.diversidade.model.Training;
import br.com.diversidade.diversidade.service.TrainingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainings")
@Tag(name = "Training", description = "Training management APIs")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    @Operation(summary = "Get all trainings", description = "Retrieves a list of all trainings")
    public ResponseEntity<List<Training>> getAllTrainings() {
        return ResponseEntity.ok(trainingService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get training by ID", description = "Retrieves a specific training by its ID")
    public ResponseEntity<Training> getTrainingById(
            @Parameter(description = "Training ID", required = true) @PathVariable Long id) {
        return trainingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new training", description = "Creates a new training")
    public ResponseEntity<Training> createTraining(
            @Parameter(description = "Training object", required = true) @RequestBody Training training) {
        return ResponseEntity.ok(trainingService.save(training));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update training", description = "Updates an existing training")
    public ResponseEntity<Training> updateTraining(
            @Parameter(description = "Training ID", required = true) @PathVariable Long id,
            @Parameter(description = "Updated training object", required = true) @RequestBody Training training) {
        return trainingService.findById(id)
                .map(existingTraining -> {
                    training.setId(id);
                    return ResponseEntity.ok(trainingService.save(training));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete training", description = "Deletes a training by its ID")
    public ResponseEntity<Void> deleteTraining(
            @Parameter(description = "Training ID", required = true) @PathVariable Long id) {
        return trainingService.findById(id)
                .map(training -> {
                    trainingService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
} 