package br.com.sigaachave.enums;

public enum StatusReserva {
	
	PENDENTE("Pendente"),
	CONFIRMADA("Confirmada"),
	CANCELADA("Cancelada");
	
	private String descricao;
	
	StatusReserva(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
