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

import com.google.gson.JsonObject;

import br.com.sigaachave.domain.JsonResponse;
import br.com.sigaachave.domain.Sala;
import br.com.sigaachave.exception.SalaException;
import br.com.sigaachave.repository.SalaRepository;

import br.com.sigaachave.service.SalaService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin("localhost:3000")
public class SalaRestController {
	
	@Autowired
	private SalaRepository salaRepository;
	
	@Autowired
	private SalaService salaService;
	
	@RequestMapping(value = "/salas", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Sala>> all() {
		return new ResponseEntity<List<Sala>>(salaRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/salas/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> get(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<String>(salaService.getSala(id).toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value = "/salas/{id}/excluir", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> remove(@PathVariable("id") Long id) {
		
		try {
			salaService.deleteSala(id);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Sala removida com sucesso!").toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/salas/adicionar/{nome}+{descricao}+{localizacao}+{permiteFixo}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> add(Sala sala){
		
		try {
			salaService.saveSala(sala);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Sala adicionada com sucesso!").toString(), HttpStatus.OK); 
		} catch (SalaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(value = "/salas/{id}/atualizar/{nome}+{descricao}+{localizacao}+{permiteFixo}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@PathVariable Long id, Sala sala) {
		
		try {
			salaService.updateSala(id, sala);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Sala atualizada com sucesso!").toString(), HttpStatus.OK); 
		} catch (SalaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
}
