package br.com.sigaachave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sigaachave.model.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	
	@Query(value = "SELECT * FROM USUARIO WHERE NOME = ?1",nativeQuery = true)
	Usuario byNome(String nome);
	
	@Query(value = "SELECT * FROM USUARIO WHERE CPF = ?1",nativeQuery = true)
	Usuario byCPF(String cpf);
}
