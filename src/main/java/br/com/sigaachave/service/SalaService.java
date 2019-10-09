package br.com.sigaachave.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigaachave.exception.SalaException;
import br.com.sigaachave.model.Sala;
import br.com.sigaachave.repository.SalaRepository;

@Service
public class SalaService {
	
	@Autowired
	private SalaRepository salaRepository;
	
	public void checkSalaById(Long id) throws SalaException {
		
		if(!salaRepository.existsById(id)) {
			throw new SalaException("Sala não encontrada!");
		}
	}
	
	public void checkSalaByNome(String nome) throws SalaException {
		
		if(salaRepository.byNome(nome).size() != 0) {
			throw new SalaException("Já existe uma sala "+nome+" no sistema!");
		}
	}
	
	public void existByNome(String nome) throws SalaException {
		
		if(salaRepository.byNome(nome).size() == 0) {
			
			 throw new SalaException("Sala não existe!");
		}
	}
	
	public void checkCamposSala(Sala sala) throws SalaException {
		
		if(sala.getNome().equals("")|| sala.getLocalizacao().equals("") || sala.getDescricao().equals("")) {
			throw new SalaException("Um ou mais campos estão vazios!");
		}
	}
	
	public Sala updateCamposSala(Long id, String nome, String localizacao, String descricao, Boolean permiteFixo) {
		
		Sala oldSala = salaRepository.getOne(id);
		
		if(nome != null) {
			oldSala.setNome(nome);
		}
		
		if(localizacao != null) {
			oldSala.setLocalizacao(localizacao);
		}
		
		if(descricao != null) {
			oldSala.setDescricao(descricao);
		}
		
		if(permiteFixo != null) {
			oldSala.setPermiteFixo(permiteFixo);
		}
		
		return oldSala;
	}
	
	public Sala getSala(Long id) throws SalaException {
		
		checkSalaById(id);
		return salaRepository.getOne(id);
	}
	
	public List<Sala> getAllSala() {
		return salaRepository.findAll();
	}
	
	public void saveSala(Sala sala) throws SalaException {
		
		checkCamposSala(sala);
		checkSalaByNome(sala.getNome());
		salaRepository.save(sala);
	}
	
	public void deleteSala(Long id) throws SalaException {
		
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
	
	public void updateSala(Long id, String nome, String localizacao, String descricao, Boolean permiteFixo) throws SalaException {
		
		checkSalaById(id);
		checkSalaByNome(nome);
		salaRepository.save(updateCamposSala(id, nome, localizacao, descricao, permiteFixo));
	}	
}
