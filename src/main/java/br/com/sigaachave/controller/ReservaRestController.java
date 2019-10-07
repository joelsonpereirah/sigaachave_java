package br.com.sigaachave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigaachave.config.JwtTokenUtil;
import br.com.sigaachave.enums.StatusReserva;
import br.com.sigaachave.exception.ReservaException;
import br.com.sigaachave.exception.StatusException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.JsonResponse;
import br.com.sigaachave.model.Reserva;
import br.com.sigaachave.service.ReservaService;
import br.com.sigaachave.service.UsuarioService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaRestController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping(value = "/reservas", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> all() {
		return new ResponseEntity<List<Reserva>>(reservaService.getAllReserva(), HttpStatus.OK);
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
	public ResponseEntity<String> add(@RequestHeader(value = "Authorization") String token, @RequestParam(value = "sala", required = true) String sala, @RequestParam(value = "diaConsulta", required = true) int diaConsulta, @RequestParam(value = "horaConsulta", required = true) int horaConsulta, @RequestParam(value = "isFixa", required = true) Boolean isFixa){
		
		
		
		Reserva reserva = new Reserva(usuarioService.getUsuarioByCPF(jwtTokenUtil.getUsernameFromToken(token)), sala, diaConsulta, horaConsulta, isFixa);
		
		reservaService.saveReserva(reserva);
		return new ResponseEntity<>(new JsonResponse(HttpStatus.OK.toString(), "Reserva adicionada com sucesso!").toString(), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/reserva/atualizar/{sala}+{data}+{status}+{isFixo}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@RequestParam(value = "id", required = true) Long id){
		
		try {
			reservaService.updateReserva(id, reserva);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Reserva atualizada com sucesso!").toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (StatusException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/status", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> getByStatus(@RequestParam(value = "status", required = true) StatusReserva status) throws Exception{
		return new ResponseEntity<List<Reserva>>(reservaService.getByStatus(status), HttpStatus.OK);
		
	}	
	
}
