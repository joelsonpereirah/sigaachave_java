package br.com.sigaachave.enums;

public enum StatusReserva {
	
	PENDENTE(0),
	CONFIRMADA(1),
	CANCELADA(2);
	
	private int valor;
	
	StatusReserva(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
}
