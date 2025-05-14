package br.com.diversidade.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "treinamento")
public class Treinamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private LocalDate data;
    private boolean obrigatorio;

    @ManyToMany
    @JoinTable(
            name = "colaborador_treinamento",
            joinColumns = @JoinColumn(name = "treinamento_id"),
            inverseJoinColumns = @JoinColumn(name = "colaborador_id")
    )
    private List<Colaborador> colaboradores = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public boolean isObrigatorio() { return obrigatorio; }
    public void setObrigatorio(boolean obrigatorio) { this.obrigatorio = obrigatorio; }

    public List<Colaborador> getColaboradores() { return colaboradores; }
    public void setColaboradores(List<Colaborador> colaboradores) { this.colaboradores = colaboradores; }
}
