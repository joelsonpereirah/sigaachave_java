package br.com.sigaachave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sigaachave.domain.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>{
	
 //precisamos implementar busca por nome para não deixar duplicatas
}
