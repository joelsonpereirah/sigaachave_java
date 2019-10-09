package br.com.sigaachave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;

import br.com.sigaachave.enums.StatusChamado;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Chamado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	@Column(name = "ID_AVALIADOR")
	private Long idAvaliador = null;
	
	@Column(name = "SALA", nullable = false)
	private String sala;
	
	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusChamado status = StatusChamado.PENDENTE;
	
	public Chamado() {}
	
	public Chamado(Usuario usuario, String sala, String descricao) {
		
		this.usuario = usuario;
		this.sala = sala;
		this.descricao = descricao;
		this.idAvaliador = null;
		this.status = StatusChamado.PENDENTE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getIdAvaliador() {
		return idAvaliador;
	}

	public void setIdAvaliador(Long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusChamado getStatus() {
		return status;
	}

	public void setStatus(StatusChamado status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		
		JsonObject object = new JsonObject();
		
		object.addProperty("id", getId());
		object.addProperty("idUsuario", usuario.getId());
		
		if(getIdAvaliador() == null) {
			object.addProperty("idAvaliador", "");
		}
		else {
			object.addProperty("idAvaliador", getIdAvaliador());
		}

		object.addProperty("sala", getSala());
		object.addProperty("descricao", getDescricao());
		object.addProperty("status", getStatus().toString());
		
		return object.toString();
	}
}
