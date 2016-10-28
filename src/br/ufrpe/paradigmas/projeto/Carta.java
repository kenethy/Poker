package br.ufrpe.paradigmas.projeto;

public class Carta {

	private int valor;
	private char naipe;
	
	Carta(int valor, char naipe){
		this.setValor(valor);
		this.setNaipe(naipe);
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public char getNaipe() {
		return naipe;
	}

	public void setNaipe(char naipe) {
		this.naipe = naipe;
	}
}
