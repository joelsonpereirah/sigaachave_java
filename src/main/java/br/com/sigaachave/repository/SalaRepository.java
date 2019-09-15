package br.com.sigaachave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sigaachave.domain.Reserva;
import br.com.sigaachave.domain.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long>{
	
 //precisamos implementar busca por nome para não deixar duplicatas
}
