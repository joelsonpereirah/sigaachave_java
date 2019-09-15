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

import br.com.sigaachave.domain.Reserva;
import br.com.sigaachave.domain.Sala;
import br.com.sigaachave.domain.Usuario;
import br.com.sigaachave.repository.SalaRepository;
import br.com.sigaachave.repository.UsuarioRepository;
import br.com.sigaachave.service.SalaService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin("localhost:3000")
public class SalaRestController {
	
	@Autowired
	private SalaRepository salaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private SalaService salaService;
	
	@RequestMapping(value = "/salas", method = RequestMethod.GET)
	public ResponseEntity<List<Sala>> all() {
		return new ResponseEntity<List<Sala>>(salaRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sala/{id}", method = RequestMethod.GET)
	public ResponseEntity<Sala> get(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<Sala>(salaService.getSala(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value = "/sala/{id}/excluir", method = RequestMethod.DELETE)
	public ResponseEntity<Sala> remove(@PathVariable("id") Long id) {
		
		try {
			salaService.deleteSala(id);
			return new ResponseEntity<Sala>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/salas/adicionar/{nome}+{descricao}+{localizacao}+{permiteFixo}", method = RequestMethod.POST)
	public ResponseEntity<Sala> add(Sala sala){
		
		try {
			salaService.saveSala(sala);
			return new ResponseEntity<>(HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} 
	}
	
}
