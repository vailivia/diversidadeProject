package br.com.diversidade.diversidade;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ColaboradorResponseDTO {

    private Long id;
    private String nome;
    private String genero;
    private String raca;
    private boolean pcd;
    private LocalDate dataContratacao;
    private boolean treinamentoDiversidade;
    private String cargo;
    private String departamento;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public ColaboradorResponseDTO(Colaborador colaborador) {
        this.id = colaborador.getId();
        this.nome = colaborador.getNome();
        this.genero = colaborador.getGenero();
        this.raca = colaborador.getRaca();
        this.pcd = colaborador.isPcd();
        this.dataContratacao = colaborador.getDataContratacao();
        this.treinamentoDiversidade = colaborador.isTreinamentoDiversidade();
        this.cargo = colaborador.getCargo();
        this.departamento = colaborador.getDepartamento();
        this.dataCriacao = colaborador.getDataCriacao();
        this.dataAtualizacao = colaborador.getDataAtualizacao();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getGenero() {
        return genero;
    }

    public String getRaca() {
        return raca;
    }

    public boolean isPcd() {
        return pcd;
    }
}
