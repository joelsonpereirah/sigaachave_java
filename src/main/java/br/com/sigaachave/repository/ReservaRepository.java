package br.com.sigaachave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sigaachave.model.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>{
	
	@Query(value = "SELECT * FROM RESERVA WHERE id_usuario = ?1",nativeQuery = true)
	List<Reserva> byUserId(Long id);
	
	@Query(value = "SELECT * FROM RESERVA WHERE status = ?1",nativeQuery = true)
	List<Reserva> findByStatus(String status);
	
	@Query(value = "SELECT * FROM RESERVA WHERE sala = ?1", nativeQuery = true)
	List<Reserva> bySala(String sala);
	
	@Query(value = "SELECT * FROM RESERVA WHERE data_consulta = ?1", nativeQuery = true)
	List<Reserva> byData(String data);
}
