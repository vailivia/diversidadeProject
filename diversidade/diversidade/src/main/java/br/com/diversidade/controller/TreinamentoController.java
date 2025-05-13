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

}
