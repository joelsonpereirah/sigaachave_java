package br.com.sigaachave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigaachave.domain.Sala;
import br.com.sigaachave.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	private SalaRepository salaRepository;
	
	public void checkSalaById(Long id) throws Exception{
		
		if(!salaRepository.existsById(id)) {
			throw new Exception("Sala não encontrada!");
		}
	}
	
	public Sala getSala(Long id) throws Exception{
		
		checkSalaById(id);
		return salaRepository.getOne(id);
	}	
	
	public void saveSala(Sala sala) throws Exception{
		
		salaRepository.save(sala);
	}
	
	public void deleteSala(Long id) throws Exception{
		
//		QUANDO A RESERVA FOR LINKADA PELO ID DA SALA
//		-------------------------------------------------		
//		List<Reserva> reservas = reservaRepository.findAll();
//		reservas.forEach(reserva -> {
//			if (reserva.getSala() == id) {
//				throw new Exception("Ainda existem reservas ativas para essa sala");
//			}
//		});
		
		checkSalaById(id);
		salaRepository.deleteById(id);
	}
	
	public void updateSala(Long id, Sala sala) throws Exception{
		
		checkSalaById(id);
		salaRepository.save(sala);
	}	
}
