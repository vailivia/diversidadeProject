package br.com.diversidade.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "colaboradores")
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 50)
    private String genero;

    @Column(length = 50)
    private String raca;

    @Column(name = "pcd")
    private boolean pcd;

    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;

    @Column(name = "treinamento_diversidade")
    private boolean treinamentoDiversidade;

    @Column(length = 100)
    private String cargo;

    @Column(length = 100)
    private String departamento;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now();
    }
}