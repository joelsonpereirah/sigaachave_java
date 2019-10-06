package br.com.sigaachave.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;

import br.com.sigaachave.enums.TipoPapel;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "CPF", updatable = false,nullable = false, length = 11)
	private String cpf;
	
	@Column(name = "NOME", nullable = false, length = 25)
	private String nome;
	
	@Column(name = "SENHA", nullable = false, length = 25)
	private String senha;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "PAPEL", nullable = false)
	private TipoPapel papel;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", targetEntity = Reserva.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reserva> reservas;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "usuario", targetEntity = Reserva.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Chamado> chamados;
	
	public Usuario() {}
	
	public Usuario(String nome, String cpf, String senha, TipoPapel papel) {
		
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.papel = papel;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoPapel getPapel() {
		return papel;
	}

	public void setPapel(TipoPapel papel) {
		this.papel = papel;
	}
	
	@Override
	public String toString() {
		
		JsonObject object = new JsonObject();
		
		object.addProperty("id", getId());
		object.addProperty("cpf", getCpf());
		object.addProperty("nome", getNome());
		object.addProperty("senha", getSenha());
		object.addProperty("papel", getPapel().toString());
		
		return object.toString();
	}
}
