package br.com.diversidade.diversidade;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ColaboradorRequestDTO {

    @NotBlank
    private String nome;

    private String genero;
    private String raca;
    private boolean pcd;
    private LocalDate dataContratacao;
    private boolean treinamentoDiversidade;
    private String cargo;
    private String departamento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public boolean isPcd() {
        return pcd;
    }

    public void setPcd(boolean pcd) {
        this.pcd = pcd;
    }
}
