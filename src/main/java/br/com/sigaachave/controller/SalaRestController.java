package br.com.sigaachave.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigaachave.exception.SalaException;
import br.com.sigaachave.model.JsonResponse;
import br.com.sigaachave.model.Sala;

import br.com.sigaachave.service.SalaService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin(origins = "http://localhost:3000")
public class SalaRestController {
	
	@Autowired
	private SalaService salaService;
	
	@RequestMapping(value = "/salas", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Sala>> all() {
		return new ResponseEntity<List<Sala>>(salaService.getAllSala(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sala", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> get(@PathParam("id") Long id) {
		
		try {
			return new ResponseEntity<String>(salaService.getSala(id).toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}	
	}
	
	@RequestMapping(value = "/sala/excluir", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> remove(@PathParam("id") Long id) {
		
		try {
			salaService.deleteSala(id);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Sala removida com sucesso!").toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	

	@RequestMapping(value = "/sala/adicionar", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> add(@RequestParam(value = "nome", required=true) String nome, @RequestParam(value = "localizacao", required=true) String localizacao, @RequestParam(value = "descricao", required=true) String descricao, @RequestParam(value = "permiteFixo", required=true) boolean permiteFixo){
		
		Sala sala = new Sala(nome, localizacao, descricao, permiteFixo);
		
		try {
			salaService.saveSala(sala);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Sala adicionada com sucesso!").toString(), HttpStatus.OK); 
		} catch (SalaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} 
	}
	
	@RequestMapping(value = "/sala/atualizar", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestParam(value = "id", required=true) Long id, @PathParam(value = "nome") String nome, @PathParam(value = "localizacao") String localizacao, @PathParam(value = "descricao") String descricao, @PathParam(value = "permiteFixo") Boolean permiteFixo) {
		
		try {
			salaService.updateSala(id, nome, localizacao, descricao, permiteFixo);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Sala atualizada com sucesso!").toString(), HttpStatus.OK); 
		} catch (SalaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
}
