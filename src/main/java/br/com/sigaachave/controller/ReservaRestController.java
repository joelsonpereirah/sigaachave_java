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

import br.com.sigaachave.enums.StatusReserva;
import br.com.sigaachave.exception.ReservaException;
import br.com.sigaachave.exception.StatusException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.JsonResponse;
import br.com.sigaachave.model.Reserva;
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
	
	@RequestMapping(value = "/reservas/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> get(@PathVariable("id") Long id) {
		
		try {
			return new ResponseEntity<String>(reservaService.getReserva(id).toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/{id}/excluir", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<String> remove(@PathVariable("id") Long id) {
		
		try {
			reservaService.deleteReserva(id);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Reserva removida com sucesso!").toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/adicionar/{sala}+{data}+{isFixo}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<String> add(Reserva reserva){
		
		reservaService.saveReserva(reserva);
		return new ResponseEntity<>(new JsonResponse(HttpStatus.OK.toString(), "Reserva adicionada com sucesso!").toString(), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/reservas/{id}/atualizar/{sala}+{data}+{status}+{isFixo}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> update(@PathVariable("id") Long id, Reserva reserva){
		
		try {
			reservaService.updateReserva(id, reserva);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Reserva atualizada com sucesso!").toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (StatusException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/{id}/usuario/{usuarioId}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> asign(@PathVariable("id") Long id, @PathVariable("usuarioId") Long usuarioId){
		
		try {
			reservaService.asignToUsuario(id, usuarioId);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Reserva atribuida ao usuário com sucesso!").toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (UsuarioException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	

	@RequestMapping(value = "/reservas/{id}/status/{status}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<String> attStatus(@PathVariable("id") Long id, @PathVariable("status") StatusReserva status){
		
		try {
			reservaService.changeStatus(id, status);
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.OK.toString(), "Status da reserva atualizado com sucesso!").toString(), HttpStatus.OK);
		} catch (ReservaException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		} catch (StatusException e) {
			return new ResponseEntity<String>(new JsonResponse(HttpStatus.NOT_FOUND.toString(), e.getMessage()).toString(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/reservas/status/{status}", method = RequestMethod.GET)
	public ResponseEntity<List<Reserva>> getByStatus(@PathVariable("status") StatusReserva status) throws Exception{
		return new ResponseEntity<List<Reserva>>(reservaService.getByStatus(status), HttpStatus.OK);
		
	}	
	
}
