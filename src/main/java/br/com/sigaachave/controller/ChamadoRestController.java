package br.com.sigaachave.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigaachave.enums.StatusChamado;
import br.com.sigaachave.exception.ChamadoException;
import br.com.sigaachave.exception.SalaException;
import br.com.sigaachave.exception.StatusException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.JsonResponse;
import br.com.sigaachave.service.ChamadoService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin(origins = "http://localhost:3000")
public class ChamadoRestController {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@RequestMapping(value = "/chamados", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> all() {
		return new ResponseEntity<String>(chamadoService.getAllChamados(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/chamado", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> get(@RequestParam(value = "id", required = true) Long id) {
		
		try {
			return new ResponseEntity<String>(chamadoService.getChamado(id).toString(), HttpStatus.OK);
		} catch (ChamadoException e) {
			return new ResponseEntity<>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/chamado/excluir", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> remove(@RequestParam(value = "id", required = true) Long id) {
		
		try {
			chamadoService.deleteChamado(id);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Chamado removido com sucesso!").toString(), HttpStatus.OK);
		} catch (ChamadoException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/chamado/adicionar", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> add(@RequestParam(value = "idUsuario") Long idUsuario, @RequestParam(value = "sala", required = true) String sala, @RequestParam(value = "descricao", required = true) String descricao){
		
		try {
			chamadoService.saveChamado(idUsuario, sala, descricao);
			return new ResponseEntity<>(new JsonResponse(HttpStatus.OK.toString(), "Chamado adicionado com sucesso!").toString(), HttpStatus.OK); 
		} catch (UsuarioException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (SalaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/chamado/atualizar", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestParam(value = "id", required = true) Long id, @PathParam(value = "status") StatusChamado status, @PathParam(value = "sala") String sala, @PathParam(value = "descricao") String descricao){
		
		try {
			chamadoService.updateChamado(id, status, sala, descricao);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Reserva atualizada com sucesso!").toString(), HttpStatus.OK);
		} catch (ChamadoException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (SalaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/chamados/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getByStatus(@RequestParam(value = "status", required = true) StatusChamado status){
		try {
			return new ResponseEntity<String>(chamadoService.getAllByStatus(status), HttpStatus.OK);
		} catch (StatusException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
		
	}
}
