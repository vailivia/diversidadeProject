package br.com.diversidade.controller;

import br.com.diversidade.model.Treinamento;
import br.com.diversidade.service.TreinamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/treinamentos")
public class TreinamentoController {

    @Autowired
    private TreinamentoService service;

    @PostMapping
    public Treinamento create(@RequestBody Treinamento t) {
        return service.save(t);
    }

    @GetMapping
    public List<Treinamento> list() {
        return service.findAll();
    }

    @PutMapping("/{treinamentoId}/associar/{colaboradorId}")
    public ResponseEntity<?> associarColaborador(@PathVariable Long treinamentoId, @PathVariable Long colaboradorId) {
        boolean associado = service.associarColaborador(treinamentoId, colaboradorId);
        if (associado) {
            return ResponseEntity.ok("Colaborador associado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Falha ao associar colaborador.");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Treinamento> atualizar(@PathVariable Long id, @RequestBody Treinamento novoTreinamento) {
        return service.findById(id)
                .map(treinamentoExistente -> {
                    treinamentoExistente.setTitulo(novoTreinamento.getTitulo());
                    treinamentoExistente.setDescricao(novoTreinamento.getDescricao());
                    treinamentoExistente.setData(novoTreinamento.getData());
                    treinamentoExistente.setObrigatorio(novoTreinamento.isObrigatorio());
                    Treinamento atualizado = service.save(treinamentoExistente);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean removido = service.delete(id);
        return removido ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


}
