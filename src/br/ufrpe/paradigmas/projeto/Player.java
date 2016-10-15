package br.ufrpe.paradigmas.projeto;

public class Player {

	private byte[] valor;
	private char[] naipe;
	Maos mao;

	public Player() {
		this.valor = new byte[5];
		this.naipe = new char[5];
	}

	public void setMao(Maos mao) {
		this.mao = mao;
	}
	
	public byte[] getValor() {
		return valor;
	}

	public byte getValorIndex(byte index){
		return valor[index];
	}

	public void setValor(byte valor, byte index) {
		this.valor[index] = valor;
	}

	public char[] getNaipe() {
		return naipe;
	}

	public char getNaipeIndex(byte index) {
		return naipe[index];
	}

	public void setNaipe(char naipe, byte index) {
		this.naipe[index] = naipe;
	}
}
