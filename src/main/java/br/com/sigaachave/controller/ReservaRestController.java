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
import br.com.sigaachave.enums.StatusReserva;
import br.com.sigaachave.repository.ReservaRepository;
import br.com.sigaachave.service.ReservaService;

@RestController
@RequestMapping("/sigaachave")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaRestController {
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@RequestMapping(value = "/reservas", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> all() {
		return new ResponseEntity<List<Reserva>>(reservaRepository.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reservas/{id}", method = RequestMethod.GET)
	public ResponseEntity<Reserva> get(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<Reserva>(reservaService.getReserva(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/{id}/excluir", method = RequestMethod.DELETE)
	public ResponseEntity<Reserva> remove(@PathVariable("id") Long id) {
		
		try {
			reservaService.deleteReserva(id);
			return new ResponseEntity<Reserva>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/adicionar/{sala}+{data}+{isFixo}", method = RequestMethod.POST)
	public ResponseEntity<Reserva> add(Reserva reserva){
		
		reservaService.saveReserva(reserva);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/reservas/{id}/atualizar/{sala}+{data}+{status}+{isFixo}", method = RequestMethod.PUT)
	public ResponseEntity<Reserva> update(@PathVariable("id") Long id, Reserva reserva){
		
		try {
			reservaService.updateReserva(id, reserva);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/{id}/usuario/{usuarioId}", method = RequestMethod.PUT)
	public ResponseEntity<Reserva> asign(@PathVariable("id") Long id, @PathVariable("usuarioId") Long usuarioId){
		
		try {
			reservaService.asignToUsuario(id, usuarioId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/status/{id}+{status}", method = RequestMethod.PUT)
	public ResponseEntity<Reserva> attStatus(@PathVariable("id") Long id, @PathVariable("status") StatusReserva status){
		
		try {
			reservaService.changeStatus(id, status);
			return new ResponseEntity<Reserva>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Reserva>(HttpStatus.NOT_FOUND);
		}
	}	
}
