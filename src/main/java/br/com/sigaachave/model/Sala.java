package br.com.sigaachave.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.JsonObject;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOME", nullable = false, length = 25)
	private String nome;
	
	@Column(name = "LOCALIZACAO", nullable = false, length = 255)
	private String localizacao;
	
	@Column(name = "DESCRICAO", nullable = false, length = 255)
	private String descricao;
	
	@Column(name = "PERMITE_FIXO")
	private Boolean permiteFixo;
	
	public Sala() {};
	
	public Sala(Long id, String nome, String localizacao, String descricao, boolean permiteFixo) {
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
		this.descricao = descricao;
		this.permiteFixo = permiteFixo;
	}
	
	public Sala(String nome, String localizacao, String descricao, boolean permiteFixo) {
		this.nome = nome;
		this.localizacao = localizacao;
		this.descricao = descricao;
		this.permiteFixo = permiteFixo;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Boolean isPermiteFixo() {
		return permiteFixo;
	}
	public void setPermiteFixo(boolean permiteFixo) {
		this.permiteFixo = permiteFixo;
	}
	
	@Override
	public String toString() {
	
		JsonObject object = new JsonObject();
		
		object.addProperty("id", getId());
		object.addProperty("nome", getNome());
		object.addProperty("localizacao", getLocalizacao());
		object.addProperty("descricao", getDescricao());
		object.addProperty("permiteFixo", isPermiteFixo());
		
		return object.toString();
	}
}
