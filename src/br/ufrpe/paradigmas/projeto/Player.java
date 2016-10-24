package br.ufrpe.paradigmas.projeto;

import java.util.ArrayList;

public class Player {

	private byte[] valor;
	private char[] naipe;
	ArrayList<Maos> mao;
	private byte cartaAlta;
	private byte cartaRanking;

	public Player() {
		this.valor = new byte[5];
		this.naipe = new char[5];
	}

	public void setMao(Maos mao) {
		if (this.mao.size() == 0)
			this.mao.add(mao);
		else
			this.mao.set(this.mao.lastIndexOf(this.mao), mao);
	}

	public byte[] getValor() {
		return valor;
	}

	public byte getValorIndex(byte index) {
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

	public byte getCartaAlta() {
		return cartaAlta;
	}

	public void setCartaAlta(byte cartaAlta) {
		this.cartaAlta = cartaAlta;
	}

	public byte getCartaRanking() {
		return cartaRanking;
	}

	public void setCartaRanking(byte cartaRanking) {
		this.cartaRanking = cartaRanking;
	}
}
