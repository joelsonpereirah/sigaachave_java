package br.com.sigaachave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sigaachave.enums.StatusChamado;
import br.com.sigaachave.model.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
	
	@Query(value = "SELECT * FROM CHAMADO WHERE SALA = ?1",nativeQuery = true)
	List<Chamado> bySala(String sala);
	
	@Query(value = "SELECT * FROM CHAMADO WHERE status = ?1",nativeQuery = true)
	List<Chamado> byStatus(String status);
}
