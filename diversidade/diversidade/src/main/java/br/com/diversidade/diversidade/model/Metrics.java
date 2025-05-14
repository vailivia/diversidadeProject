package br.com.diversidade.diversidade.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "metrics")
public class Metrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @Column(name = "participant_count")
    private Integer participantCount = 0;

    @Column(name = "completion_rate")
    private Double completionRate = 0.0;

    @Column(name = "satisfaction_score")
    private Double satisfactionScore = 0.0;

    @Column(name = "feedback_count")
    private Integer feedbackCount = 0;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 