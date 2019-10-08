package br.com.sigaachave.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;

import br.com.sigaachave.enums.StatusReserva;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reserva {
	
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private StatusReserva status = StatusReserva.PENDENTE;
	
	@Column(name="DATA_CONSULTA")
	@CreatedDate
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Temporal(TemporalType.DATE)
	private Date dataConsulta;
	
	@Column(name = "HORA_CONSULTA")
	private int horaConsulta;
	
	@Column(name = "IS_FIXA", nullable = false)
	private Boolean isFixa;
	
	public Reserva() {}
	
	public Reserva(Usuario usuario, String sala, Date dataConsulta, int horaConsulta, Boolean isFixa) {
		
		this.usuario = usuario;
		this.sala = sala;
		this.dataConsulta = dataConsulta;
		this.horaConsulta = horaConsulta;
		this.isFixa = isFixa;
	}
	
	public Long getId() {
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
	
	public Long getIdAvaliador() {
		return idAvaliador;
	}
	public void setIdAvaliador(long idAvaliador) {
		this.idAvaliador = idAvaliador;
	}
	public Date getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public int getHoraConsulta() {
		return horaConsulta;
	}
	public void setHoraConsulta(int horaConsulta) {
		this.horaConsulta = horaConsulta;
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
	
	public Boolean isFixa() {
		return isFixa;
	}
	public void setFixa(boolean isFixa) {
		this.isFixa = isFixa;
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
		object.addProperty("diaConsulta", getDataConsulta().toString());
		object.addProperty("horaConsulta", getHoraConsulta());
		object.addProperty("isFixo", isFixa());
		object.addProperty("status", getStatus().toString());
		
		return object.toString();
	}
}
