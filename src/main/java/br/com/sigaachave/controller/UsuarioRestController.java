package br.com.sigaachave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigaachave.exception.PapelException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.JsonResponse;
import br.com.sigaachave.model.Reserva;
import br.com.sigaachave.model.Usuario;
import br.com.sigaachave.service.ReservaService;
import br.com.sigaachave.service.UsuarioService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioRestController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ReservaService reservaService;
	
	@RequestMapping(value = "/usuarios", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> all() {
		return new ResponseEntity<List<Usuario>>(usuarioService.getAllUsuario(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/usuarios/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> get(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<String>(usuarioService.getUsuario(id).toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value = "/usuarios/{id}/excluir", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> remove(@PathVariable("id") Long id) {
		
		try {
			usuarioService.deleteUsuario(id);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Usuário excluído com sucesso!").toString(), HttpStatus.OK);
		} catch (UsuarioException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/usuarios/adicionar/{nome}+{senha}+{papel}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> add(Usuario usuario){
		
		try {
			usuarioService.saveUsuario(usuario);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Usuário adicionado com sucesso!").toString(), HttpStatus.OK); 
		} catch (PapelException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(value = "/usuarios/{id}/atualizar/{nome}+{senha}+{papel}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@PathVariable("id") Long id, Usuario usuario){
		
		try {
			usuarioService.updateUsuario(id, usuario);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Usuário atualizado com sucesso!").toString(), HttpStatus.OK);
		} catch (UsuarioException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/usuarios/{id}/reservas", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> getByUser(@PathVariable("id") Long id){
		
		List<Reserva> reservas;
		try {
			reservas = reservaService.getReservaByUserId(id);
			return new ResponseEntity<List<Reserva>>(reservas, HttpStatus.OK);
		} catch (UsuarioException e) {
			return new ResponseEntity<List<Reserva>>(HttpStatus.NOT_FOUND);
		}
	}
}
