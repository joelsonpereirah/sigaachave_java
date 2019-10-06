package br.com.sigaachave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sigaachave.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	@Query(value = "SELECT * FROM RESERVA WHERE usuario_id = ?1",nativeQuery = true)
	List<Reserva> byUserId(Long id);
	
	@Query(value = "SELECT * FROM RESERVA WHERE status = ?1",nativeQuery = true)
	List<Reserva> findByStatus(String status);
}
