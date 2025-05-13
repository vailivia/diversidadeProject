package br.com.diversidade.controller; // ajuste o pacote conforme seu projeto

import br.com.diversidade.service.ColaboradorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    // Construtor com injeção do serviço
    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping("/countPorGenero")
    public Map<String, Long> contarColaboradoresPorGenero() {
        return colaboradorService.contarPorGenero();
    }

    //Exemplo de endpoint usando o service
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        colaboradorService.delete(id);
        return ResponseEntity.ok("Deletado com sucesso");
    }

    // add outros endpoints
}
