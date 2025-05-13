package br.com.diversidade.controller;

import br.com.diversidade.model.ParticipacaoTreinamento;
import br.com.diversidade.service.ParticipacaoTreinamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/participacoes")
public class ParticipacaoTreinamentoController {

    private final ParticipacaoTreinamentoService service;

    public ParticipacaoTreinamentoController(ParticipacaoTreinamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ParticipacaoTreinamento> criar(@RequestBody ParticipacaoTreinamento pt) {
        return ResponseEntity.ok(service.salvar(pt));
    }

    @GetMapping
    public List<ParticipacaoTreinamento> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/colaborador/{id}")
    public List<ParticipacaoTreinamento> listarPorColaborador(@PathVariable Long id) {
        return service.listarPorColaborador(id);
    }
}
