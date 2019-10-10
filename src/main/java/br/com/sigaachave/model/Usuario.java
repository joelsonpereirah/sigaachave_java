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
	@Column(name = "ID", nullable = false, unique = true)
	private Long id;
	
	@Column(name = "CPF", updatable = false,nullable = false, length = 11)
	private String cpf;
	
	@Column(name = "NOME", nullable = false, length = 25)
	private String nome;
	
	@Column(name = "SENHA", nullable = false, length = 25)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "PAPEL", nullable = false)
	private TipoPapel papel;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", targetEntity = Reserva.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Reserva> reservas;
	
	@JsonIgnore
	@OneToMany(mappedBy = "usuario", targetEntity = Reserva.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Chamado> chamados;
	
	public Usuario() {}
	
	public Usuario(String nome, String cpf, String senha, TipoPapel papel) {
		
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
		this.papel = papel;
	}
	
	public Long getId() {
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
	
	// Uses Caesar Cipher idea
	public Usuario Encrypt() {
		int module;
		int key = Integer.parseUnsignedInt(cpf);
		
		char chars[] = senha.toCharArray();
		for(char ch : chars)
		{
			//Números e alguns caracteres especiais
			if(33 <= ch & ch <= 64)
			{
				module = 32;
				key = key % module;
				ch = (char) ((ch + key - 33) % module + 33);
			}
			//Letras maiusculas
			else if( 65 <= ch & ch <= 90)
			{
				module = 26;
				key = key % module;
				ch = (char) ((ch + key - 65) % module + 65);
			}
			//Letras minusculas
			else if(97 <= ch & ch <= 122)
			{
				module = 26;
				key = key % module;
				ch = (char) ((ch + key - 97) % module + 97);				
			}
		}
		this.senha = chars.toString();
		
		return this;
	}
	
	// Uses Caesar Cipher idea
	public Usuario Decrypt() {
		int module;
		int key = Integer.parseUnsignedInt(cpf);
		
		char chars[] = senha.toCharArray();
		for(char ch : chars)
		{
			//Números e alguns caracteres especiais(não todos)
			if(33 <= ch & ch <= 64)
			{
				module = 32;
				key = key % module;
				key = module - key; // Para avançarmos oq regredimos
				ch = (char) ((ch + key - 33) % module + 33);
			}
			//Letras maiusculas
			else if( 65 <= ch & ch <= 90)
			{
				module = 26;
				key = key % module;
				key = module - key; // Para avançarmos oq regredimos
				ch = (char) ((ch + key - 65) % module + 65);
			}
			//Letras minusculas
			else if(97 <= ch & ch <= 122)
			{
				module = 26;
				key = key % module;
				key = module - key; // Para avançarmos oq regredimos
				ch = (char) ((ch + key - 97) % module + 97);				
			}
		}
		this.senha = chars.toString();
		
		return this;
	}
}
