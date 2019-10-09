package br.com.sigaachave.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
	
	public Reserva updateCamposSala(Long id, String sala, String dataConsulta, Integer horaConsulta, Boolean isFixa) throws ParseException {
		
		Reserva oldReserva = reservaRepository.getOne(id);
		
		if(sala != null) {
			oldReserva.setSala(sala);
		}
		if(dataConsulta != null) {
			oldReserva.setDataConsulta(stringToDate(dataConsulta));
		}
		if(horaConsulta != null) {
			oldReserva.setHoraConsulta(horaConsulta);
		}
		if(isFixa != null) {
			oldReserva.setFixa(isFixa);
		}
		
		return oldReserva;
	}
	
	public Date stringToDate(String date) throws ParseException {
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");
		
		return formato.parse(date);
	}
	
	public String listToJson(List<Reserva> reservas) {
		
		String json = "[";
		
		for(int i = 0; i < reservas.size(); i++) {
			
			json += reservas.get(i).toString();
			
			if(i < reservas.size()-1) {
				json += ",";
			}
		}
		
		json += "]";
		
		return json;
	}
	
	public Reserva getReserva(Long id) throws ReservaException {
		
		checkReservabyId(id);
		return reservaRepository.getOne(id);
	}
	
	public String getAllReserva() {
		
		List<Reserva> reservas =  reservaRepository.findAll();
		
		return listToJson(reservas);
	}
	
	public String getReservaByUserId(Long id) throws UsuarioException {
		
		usuarioService.checkUsuariobyId(id);
		return listToJson(reservaRepository.byUserId(id));
	}
	
	public void deleteReserva(Long id) throws ReservaException{
		
		checkReservabyId(id);
		reservaRepository.deleteById(id);
	}
	
	public void saveReserva(Long idUsuario, String sala, String dataConsulta, int horaConsulta, Boolean isFixa) throws UsuarioException, ParseException {
		
		Reserva reserva = new Reserva(usuarioService.getUsuario(idUsuario), sala, stringToDate(dataConsulta), horaConsulta, isFixa);
		
		reserva.setStatus(StatusReserva.PENDENTE);
		reservaRepository.save(reserva);
	}
	
	public void updateReserva(Long id, String sala, String dataConsulta, Integer horaConsulta, Boolean isFixa) throws ReservaException, StatusException, ParseException {
		
		checkReservabyId(id);
		reservaRepository.save(updateCamposSala(id, sala, dataConsulta, horaConsulta, isFixa));
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
	
	public String getByStatus(StatusReserva status) throws Exception {
		
		checkStatus(status);
		List<Reserva> reservas = reservaRepository.findByStatus(status.toString());
		
		return listToJson(reservas);
	}
	
	public String getByData(String data) throws ParseException {
		
		return listToJson(reservaRepository.byData(data));
	}
}
