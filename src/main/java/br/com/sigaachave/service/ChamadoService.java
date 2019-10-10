package br.com.sigaachave.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sigaachave.enums.StatusChamado;
import br.com.sigaachave.exception.ChamadoException;
import br.com.sigaachave.exception.SalaException;
import br.com.sigaachave.exception.StatusException;
import br.com.sigaachave.exception.UsuarioException;
import br.com.sigaachave.model.Chamado;
import br.com.sigaachave.repository.ChamadoRepository;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRepository chamadoRepository;
	
	@Autowired
	private SalaService salaService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	private void checkChamadoById(Long id) throws ChamadoException{
		
		if(!chamadoRepository.existsById(id)) {
			throw new ChamadoException("Chamado não encontrado!");
		}
	}
	
	private void checkStatus(StatusChamado status) throws StatusException{
		
		List<StatusChamado> tipoStatus = Arrays.asList(StatusChamado.values());
		
		if(status == null) {
			throw new StatusException("Status vazio!");
		}
		
		if(!tipoStatus.contains(status)) {
			throw new StatusException("Status inválido!");
		}
	}
	
	public String listToString(List<Chamado> chamados) {
		
		String json = "[";
		
		for(int i = 0; i < chamados.size(); i++) {
			
			json += chamados.get(i).toString();
			
			if(i < chamados.size()-1) {
				json += ",";
			}
		}
		
		json += "]";
		
		return json;
	}
	
	public Chamado updateCamposChamado(Long id, StatusChamado status, String sala, String descricao) {
		
		Chamado oldChamado = chamadoRepository.getOne(id);

		if(status != null) {
			oldChamado.setStatus(verifyAndamento(oldChamado, status));
		}
		if(sala != null) {
			oldChamado.setSala(sala);
		}
		if(descricao != null) {
			oldChamado.setDescricao(descricao);
		}
		
		return oldChamado;
	}

	private StatusChamado verifyAndamento(Chamado chamado, StatusChamado novoStatus) {
		if (chamado.getStatus().equals(StatusChamado.EM_EXECUCAO) && novoStatus.equals(StatusChamado.CONFIRMADO)){
			return StatusChamado.FINALIZADO;
		} else {
			return novoStatus;
		}
	}
	
	public String getAllChamados() {
		
		return listToString(chamadoRepository.findAll());
	}
	
	public Chamado getChamado(Long id) throws ChamadoException {
		
		checkChamadoById(id);
		return chamadoRepository.getOne(id);
	}
	
	public void deleteChamado(Long id) throws ChamadoException {
		
		checkChamadoById(id);
		chamadoRepository.deleteById(id);
	}
	
	public void saveChamado(Long idUsuario, String sala, String descricao) throws UsuarioException, SalaException {
		
		usuarioService.checkUsuariobyId(idUsuario);
		salaService.existByNome(sala);
		Chamado chamado = new Chamado(usuarioService.getUsuario(idUsuario), sala, descricao);
		chamadoRepository.save(chamado);
	}
	
	public void updateChamado(Long id, StatusChamado status, String sala, String descricao) throws ChamadoException, SalaException {
		
		checkChamadoById(id);
		salaService.existByNome(sala);
		chamadoRepository.save(updateCamposChamado(id, status, sala, descricao));
	}
	
	public String getAllByStatus(StatusChamado status) throws StatusException {
		
		checkStatus(status);
		return listToString(chamadoRepository.byStatus(status.toString()));
	}
}
