package br.com.sigaachave.controller;

import java.text.ParseException;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigaachave.enums.StatusReserva;
import br.com.sigaachave.exception.ReservaException;
import br.com.sigaachave.exception.StatusException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.JsonResponse;
import br.com.sigaachave.service.ReservaService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaRestController {
	
	@Autowired
	private ReservaService reservaService;
	
	@RequestMapping(value = "/reservas", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> all() {
		return new ResponseEntity<String>(reservaService.getAllReserva(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reserva", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> get(@RequestParam(value = "id", required = true) Long id) {
		
		try {
			return new ResponseEntity<String>(reservaService.getReserva(id).toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reserva/excluir", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> remove(@RequestParam(value = "id", required = true) Long id) {
		
		try {
			reservaService.deleteReserva(id);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Reserva removida com sucesso!").toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reserva/adicionar", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> add(@RequestParam(value = "idUsuario") Long idUsuario, @RequestParam(value = "sala", required = true) String sala, @RequestParam(value = "dataConsulta", required = true) String dataConsulta, @RequestParam(value = "horaConsulta", required = true) int horaConsulta, @RequestParam(value = "isFixa", required = true) Boolean isFixa){
		
		try {
			reservaService.saveReserva( idUsuario, sala, dataConsulta, horaConsulta, isFixa);
			return new ResponseEntity<>(new JsonResponse(HttpStatus.OK.toString(), "Reserva adicionada com sucesso!").toString(), HttpStatus.OK); 
		} catch (UsuarioException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (ParseException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reserva/atualizar", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestParam(value = "id", required = true) Long id, @PathParam(value = "status") StatusReserva status, @PathParam(value = "sala") String sala, @PathParam(value = "dataConsulta") String dataConsulta, @PathParam(value = "horaConsulta") Integer horaConsulta, @PathParam(value = "isFixa") Boolean isFixa){
		
		try {
			reservaService.updateReserva(id, status, sala, dataConsulta, horaConsulta, isFixa);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Reserva atualizada com sucesso!").toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (StatusException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (ParseException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/status", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getByStatus(@RequestParam(value = "status", required = true) StatusReserva status) throws Exception{
		return new ResponseEntity<String>(reservaService.getByStatus(status), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservas/usuario", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getByUsuario(@RequestParam(value = "idUsuario", required = true) Long idUsuario){
		try {
			return new ResponseEntity<String>(reservaService.getReservaByUserId(idUsuario), HttpStatus.OK);
		} catch (UsuarioException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/data", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> getByData(@RequestParam(value = "data", required = true) String data){
		try {
			return new ResponseEntity<String>(reservaService.getByData(data), HttpStatus.OK);
		} catch (ParseException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
}
