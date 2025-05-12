package br.com.diversidade.diversidade;

import br.com.diversidade.diversidade.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
}