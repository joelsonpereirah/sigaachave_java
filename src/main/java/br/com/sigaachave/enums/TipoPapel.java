package br.com.sigaachave.enums;

public enum TipoPapel {
	
	BOLSISTA(0),
	COORDENADOR(1),
	ADMIN(2);
	
	private int valor;
	
	TipoPapel(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
		return valor;
	}
}
