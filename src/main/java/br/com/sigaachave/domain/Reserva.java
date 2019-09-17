package br.com.sigaachave.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;

import br.com.sigaachave.enums.StatusReserva;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String sala;
	private String data;
	private boolean isFixo;
	
	@Enumerated(EnumType.STRING)
	private StatusReserva status;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	@JsonIgnore
	private Usuario usuario;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSala() {
		return sala;
	}
	public void setSala(String sala) {
		this.sala = sala;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public StatusReserva getStatus() {
		return status;
	}
	public void setStatus(StatusReserva status) {
		this.status = status;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean getIsFixo() {
		return isFixo;
	}
	public void setFixo(boolean isFixo) {
		this.isFixo = isFixo;
	}
	
	@Override
	public String toString() {
		
		JsonObject object = new JsonObject();
		
		object.addProperty("id", getId());
		object.addProperty("sala", getSala());
		object.addProperty("data", getData());
		object.addProperty("isFixo", getIsFixo());
		object.addProperty("status", getStatus().toString());
		
		return object.toString();
	}
}
