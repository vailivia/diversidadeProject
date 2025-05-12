package br.com.diversidade.diversidade;

import br.com.diversidade.diversidade.ColaboradorRequestDTO;
import br.com.diversidade.diversidade.ColaboradorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    @Autowired
    private ColaboradorService service;

    @Autowired
    private ColaboradorRepository repository;

    @GetMapping
    public List<ColaboradorResponseDTO> listar() {
        return service.listar().stream()
                .map(ColaboradorResponseDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponseDTO> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(colaborador -> ResponseEntity.ok(new ColaboradorResponseDTO(colaborador)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ColaboradorResponseDTO> criar(@RequestBody @Valid ColaboradorRequestDTO dto) {
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(dto.getNome());
        colaborador.setGenero(dto.getGenero());
        colaborador.setRaca(dto.getRaca());
        colaborador.setPcd(dto.isPcd());

        Colaborador salvo = service.salvar(colaborador);
        return ResponseEntity.ok(new ColaboradorResponseDTO(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid ColaboradorRequestDTO dto) {
        return repository.findById(id)
                .map(colaborador -> {
                    colaborador.setNome(dto.getNome());
                    colaborador.setGenero(dto.getGenero());
                    colaborador.setRaca(dto.getRaca());
                    colaborador.setPcd(dto.isPcd());

                    Colaborador atualizado = repository.save(colaborador);
                    return ResponseEntity.ok(new ColaboradorResponseDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return repository.findById(id)
                .<ResponseEntity<Void>>map(colaborador -> {
                    repository.delete(colaborador);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/todos")
    public List<ColaboradorResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(ColaboradorResponseDTO::new)
                .collect(Collectors.toList());
    }
}

