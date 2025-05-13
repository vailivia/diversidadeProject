package br.com.diversidade.repository;

import br.com.diversidade.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    @Query("SELECT c.genero, COUNT(c) FROM Colaborador c GROUP BY c.genero")
    List<Object[]> countColaboradoresPorGenero();

}
