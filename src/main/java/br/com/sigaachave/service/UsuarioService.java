package br.com.sigaachave.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigaachave.domain.Usuario;
import br.com.sigaachave.enums.TipoPapel;
import br.com.sigaachave.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void checkUsuariobyId(Long id) throws Exception{
		
		if(!usuarioRepository.existsById(id)) {
			throw new Exception("Usuário não encontrado!");
		}
	}
	
	private void checkPapel(TipoPapel papel) throws Exception{
		
		List<TipoPapel> papeis = Arrays.asList(TipoPapel.values());
		
		if(papel.toString().equals("")) {
			throw new Exception("Papel vazio!");
		}
		
		if(!papeis.contains(papel)) {
			throw new Exception("Papel inválido!");
		}
	}
	
	public Usuario getUsuario(Long id) throws Exception{
		
		checkUsuariobyId(id);
		return usuarioRepository.getOne(id);
	}
	
	public void saveUsuario(Usuario usuario) throws Exception{
		
		checkPapel(usuario.getPapel());
		usuarioRepository.save(usuario);
	}
	
	public void deleteUsuario(Long id) throws Exception{
		
		checkUsuariobyId(id);
		usuarioRepository.deleteById(id);
	}
	
	public void updateUsuario(Long id, Usuario usuario) throws Exception{
		
		checkUsuariobyId(id);
		usuarioRepository.save(usuario);
	}
}
