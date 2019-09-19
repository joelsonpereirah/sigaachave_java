package br.com.sigaachave.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigaachave.enums.StatusReserva;
import br.com.sigaachave.exception.ReservaException;
import br.com.sigaachave.exception.StatusException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.Reserva;
import br.com.sigaachave.model.Usuario;
import br.com.sigaachave.repository.ReservaRepository;
import br.com.sigaachave.repository.UsuarioRepository;

@Service
public class ReservaService {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private void checkReservabyId(Long id) throws ReservaException{
		
		if(!reservaRepository.existsById(id)) {
			throw new ReservaException("Reserva não encontrada!");
		}
	}
	
	private void checkStatus(StatusReserva status) throws StatusException{
		
		List<StatusReserva> tipoStatus = Arrays.asList(StatusReserva.values());
		
		if(status.toString().equals("")) {
			throw new StatusException("Status vazio!");
		}
		
		if(!tipoStatus.contains(status)) {
			throw new StatusException("Status inválido!");
		}
	}
	
	public Reserva getReserva(Long id) throws ReservaException {
		
		checkReservabyId(id);
		return reservaRepository.getOne(id);
	}
	
	public List<Reserva> getAllReserva() {
		
		return reservaRepository.findAll();
	}
	
	public List<Reserva> getReservaByUserId(Long id) throws UsuarioException {
		
		usuarioService.checkUsuariobyId(id);
		return reservaRepository.byUserId(id);
	}
	
	public void deleteReserva(Long id) throws ReservaException{
		
		checkReservabyId(id);
		reservaRepository.deleteById(id);
	}
	
	public void saveReserva(Reserva reserva) {
		
		reserva.setStatus(StatusReserva.PENDENTE);
		reservaRepository.save(reserva);
	}
	
	public void updateReserva(Long id, Reserva reserva) throws ReservaException, StatusException {
		
		checkReservabyId(id);
		checkStatus(reserva.getStatus());
		reservaRepository.save(reserva);
	}
	
	public void asignToUsuario(Long id, Long usuarioId) throws ReservaException, UsuarioException {
		
		checkReservabyId(id);
		usuarioService.checkUsuariobyId(usuarioId);
		
		Optional<Reserva> reserva = reservaRepository.findById(id);
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		reserva.get().setUsuario(usuario.get());
		reservaRepository.save(reserva.get());
	}
	
	public void changeStatus(Long id, StatusReserva status) throws ReservaException, StatusException {
		
		checkReservabyId(id);
		checkStatus(status);
		
		Optional<Reserva> reserva = reservaRepository.findById(id);
		reserva.get().setStatus(status);
		reservaRepository.save(reserva.get());
	}
	
	public List<Reserva> getByStatus(StatusReserva status) throws Exception {
		
		checkStatus(status);
		List<Reserva> reservas = reservaRepository.findByStatus(status.toString());
		
		return reservas;
	}
}
