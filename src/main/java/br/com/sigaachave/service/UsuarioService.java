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
	
	public Usuario getUsuario(Long id) throws UsuarioException{
		
		checkUsuariobyId(id);
		return usuarioRepository.getOne(id);
	}
	
	public List<Usuario> getAllUsuario() {
		
		return usuarioRepository.findAll();
	}
	
	public void saveUsuario(Usuario usuario) throws PapelException{
		
		checkPapel(usuario.getPapel());
		usuarioRepository.save(usuario);
	}
	
	public void deleteUsuario(Long id) throws UsuarioException{
		
		checkUsuariobyId(id);
		usuarioRepository.deleteById(id);
	}
	
	public void updateUsuario(Long id, Usuario usuario) throws UsuarioException{
		
		checkUsuariobyId(id);
		usuarioRepository.save(usuario);
	}
	
	public Usuario getUsuarioByNome(String nome) {
		
		return usuarioRepository.byNome(nome);
	}
}
