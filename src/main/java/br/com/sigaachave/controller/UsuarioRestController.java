package br.com.sigaachave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigaachave.enums.TipoPapel;
import br.com.sigaachave.exception.PapelException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.JsonResponse;
import br.com.sigaachave.model.Usuario;
import br.com.sigaachave.service.UsuarioService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> all() {
		return new ResponseEntity<List<Usuario>>(usuarioService.getAllUsuario(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuario", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> get(@RequestParam(name = "id", required = true) Long id) {
		
		try {
			return new ResponseEntity<String>(usuarioService.getUsuario(id).toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value = "/usuario/excluir", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> remove(@RequestParam(name = "id", required = true) Long id) {
		
		try {
			usuarioService.deleteUsuario(id);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Usuário excluído com sucesso!").toString(), HttpStatus.OK);
		} catch (UsuarioException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/usuario/adicionar", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> add(@RequestParam(name = "nome", required = true) String nome, @RequestParam(name = "cpf", required  = true) String cpf, @RequestParam(name = "senha", required = true) String senha, @RequestParam(name = "papel", required = true) TipoPapel papel){
		
		Usuario usuario = new Usuario(nome, cpf, senha, papel);
		
		try {
			usuarioService.saveUsuario(usuario);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Usuário adicionado com sucesso!").toString(), HttpStatus.OK); 
		} catch (PapelException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/usuario/atualizar", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestParam(name = "id", required = true) Long id, @RequestParam(name = "nome") String nome, @RequestParam(name = "cpf") String cpf, @RequestParam(name = "senha") String senha, @RequestParam(name = "papel") TipoPapel papel){
		
		try {
			usuarioService.updateUsuario(id, nome, cpf, senha, papel);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Usuário atualizado com sucesso!").toString(), HttpStatus.OK);
		} catch (UsuarioException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (PapelException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
}
