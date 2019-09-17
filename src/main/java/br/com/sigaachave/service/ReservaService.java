package br.com.sigaachave.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigaachave.domain.Reserva;
import br.com.sigaachave.domain.Usuario;
import br.com.sigaachave.enums.StatusReserva;

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
	
	private void checkReservabyId(Long id) throws Exception{
		
		if(!reservaRepository.existsById(id)) {
			throw new Exception("Reserva não encontrada!");
		}
	}
	
	private void checkStatus(StatusReserva status) throws Exception{
		
		List<StatusReserva> tipoStatus = Arrays.asList(StatusReserva.values());
		
		if(status.toString().equals("")) {
			throw new Exception("Status vazio!");
		}
		
		if(!tipoStatus.contains(status)) {
			throw new Exception("Status inválido!");
		}
	}
	
	public Reserva getReserva(Long id) throws Exception {
		
		checkReservabyId(id);
		return reservaRepository.getOne(id);
	}
	
	public void deleteReserva(Long id) throws Exception {
		
		checkReservabyId(id);
		reservaRepository.deleteById(id);
	}
	
	public void saveReserva(Reserva reserva) {
		
		reserva.setStatus(StatusReserva.PENDENTE);
		reservaRepository.save(reserva);
	}
	
	public void updateReserva(Long id, Reserva reserva) throws Exception {
		
		checkReservabyId(id);
		reservaRepository.save(reserva);
	}
	
	public void asignToUsuario(Long id, Long usuarioId) throws Exception {
		
		checkReservabyId(id);
		usuarioService.checkUsuariobyId(usuarioId);
		
		Optional<Reserva> reserva = reservaRepository.findById(id);
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		reserva.get().setUsuario(usuario.get());
		reservaRepository.save(reserva.get());
	}
	
	public void changeStatus(Long id, StatusReserva status) throws Exception {
		
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
