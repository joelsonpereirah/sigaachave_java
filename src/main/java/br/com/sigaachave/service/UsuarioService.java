package br.com.sigaachave.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigaachave.enums.TipoPapel;
import br.com.sigaachave.exception.PapelException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.Usuario;
import br.com.sigaachave.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void checkUsuariobyId(Long id) throws UsuarioException{
		
		if(!usuarioRepository.existsById(id)) {
			throw new UsuarioException("Usuário não encontrado!");
		}
	}
	
	private void checkPapel(TipoPapel papel) throws PapelException{
		
		List<TipoPapel> papeis = Arrays.asList(TipoPapel.values());
		
		if(papel.toString().equals("")) {
			throw new PapelException("Papel vazio!");
		}
		
		if(!papeis.contains(papel)) {
			throw new PapelException("Papel inválido!");
		}
	}
	
	private void checkUsuarioByCPF(String cpf) throws Exception {
		
		Usuario usuario = usuarioRepository.byCPF(cpf);
		
		if(usuario != null) {
			throw new Exception("CPF já cadastrado!");
		}
	}
	
	public Usuario updateCamposUsuario(Long id, String nome, String cpf, String senha, TipoPapel papel) throws PapelException {
		
		Usuario usuario = usuarioRepository.getOne(id);
		
		if(nome != null) {
			usuario.setNome(nome);
		}
		
		if(cpf != null) {
			usuario.setCpf(cpf);
		}
		
		if(senha != null) {
			usuario.setSenha(senha);
		}
		
		if(papel != null) {
			checkPapel(papel);
			usuario.setPapel(papel);
		}
		
		return usuario;
	}
	
	public Usuario getUsuario(Long id) throws UsuarioException{
		
		checkUsuariobyId(id);
		return usuarioRepository.getOne(id);
	}
	
	public List<Usuario> getAllUsuario() {
		
		return usuarioRepository.findAll();
	}
	
	public void saveUsuario(Usuario usuario) throws PapelException, Exception{
		
		checkPapel(usuario.getPapel());
		checkUsuarioByCPF(usuario.getCpf());
		usuarioRepository.save(usuario);
		//usuarioRepository.save(usuario.Encrypt());
	}
	
	public void deleteUsuario(Long id) throws UsuarioException{
		
		checkUsuariobyId(id);
		usuarioRepository.deleteById(id);
	}
	
	public void updateUsuario(Long id, String nome, String cpf, String senha, TipoPapel papel) throws UsuarioException, PapelException{
		
		checkUsuariobyId(id);
		usuarioRepository.save(updateCamposUsuario(id, nome, cpf, senha, papel));
		//usuarioRepository.save(updateCamposUsuario(id, nome, cpf, senha, papel).Encrypt());

	}
	
	public Usuario getUsuarioByNome(String nome) {
		
		return usuarioRepository.byNome(nome);
	}
	
	public Usuario getUsuarioByCPF(String cpf) {
		
		return usuarioRepository.byCPF(cpf);
	}
}
