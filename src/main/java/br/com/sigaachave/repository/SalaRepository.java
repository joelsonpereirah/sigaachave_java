package br.com.sigaachave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sigaachave.model.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long>{
	
	@Query(value = "SELECT * FROM SALA WHERE NOME = ?1",nativeQuery = true)
	List<Sala> byNome(String nome);
}
