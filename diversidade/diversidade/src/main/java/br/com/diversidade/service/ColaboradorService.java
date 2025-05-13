package br.com.diversidade.service;
import br.com.diversidade.model.Colaborador;
import br.com.diversidade.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ColaboradorService {

    @Autowired
    private ColaboradorRepository repository;

    public Colaborador salvar(Colaborador colaborador) {
        return repository.save(colaborador);
    }

    public List<Colaborador> listar() {
        return repository.findAll();
    }

    public Map<String, Long> contarPorGenero() {
        List<Object[]> resultados = repository.countColaboradoresPorGenero();
        Map<String, Long> contagem = new HashMap<>();
        for (Object[] resultado : resultados) {
            String genero = (String) resultado[0];
            Long total = (Long) resultado[1];
            contagem.put(genero, total);
        }
        return contagem;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
